package br.unitins.pibiti.resource;

import java.io.IOException;

import br.unitins.pibiti.dto.nit.NitUpdateDTO;
import br.unitins.pibiti.dto.nit.SenhaDTO;
import br.unitins.pibiti.dto.nit.ServicosFornecidoDTO;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import br.unitins.pibiti.application.Result;
import br.unitins.pibiti.dto.nit.NitDTO;
import br.unitins.pibiti.dto.nit.NitResponseDTO;
import br.unitins.pibiti.form.NitImageForm;
import br.unitins.pibiti.repository.NitRepository;
import br.unitins.pibiti.service.auth.JwtService;
import br.unitins.pibiti.service.file.NitFileService;
import br.unitins.pibiti.service.nit.NitService;
import io.quarkus.security.Authenticated;
import io.smallrye.jwt.auth.principal.ParseException;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;
import jakarta.ws.rs.core.Response.Status;

@Path("/nits")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class NitResource {
    
    @Inject
    NitService nitService;

    @Inject
    NitRepository nitRepository;

    @Inject
    NitFileService nitFileService;

    @Inject
    JwtService jwtService;

    @Inject
    JsonWebToken jwt;

    @GET
    @Path("/{id}")
    @Authenticated
    public NitResponseDTO getNit(@PathParam("id") Long id) throws NotFoundException {

        return nitService.getNit(id);
    }

    @POST
    public Response cadastrar(NitDTO nitDTO) {

        Result result;

        try {

            return Response
                    .status(Status.CREATED) // 201
                    .entity(nitService.cadastrar(nitDTO))
                    .build();
        } catch (ConstraintViolationException e) {

            result = new Result(e.getConstraintViolations());

            return Response
                    .status(Status.BAD_REQUEST)
                    .entity(result)
                    .build();
        } catch (Exception e) {

            result = new Result(e.getMessage(), false);

            return Response
                    .status(Status.BAD_REQUEST)
                    .entity(result)
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(NitUpdateDTO nitDTO) {

        Result result;

        String cnpj = jwt.getSubject();

        try {

            nitService.atualizar(cnpj, nitDTO);

            return Response
                    .status(Status.NO_CONTENT) // 204
                    .build();
        } catch (ConstraintViolationException e) {

            result = new Result(e.getConstraintViolations());

            return Response
                    .status(Status.BAD_REQUEST)
                    .entity(result)
                    .build();
        } catch (Exception e) {

            result = new Result(e.getMessage(), false);

            return Response
                    .status(Status.BAD_REQUEST)
                    .entity(result)
                    .build();
        }
    }

    @POST
    @Path("/cadastrar-servicos-fornecidos")
    @Authenticated
    public Response cadastrarServicosFornecidos(ServicosFornecidoDTO servicosFornecidoDTO) {

        Result result;

        try {
            nitService.cadastrarServicosFornecidos(servicosFornecidoDTO);

            return Response
                    .status(Status.NO_CONTENT) // 201
                    .build();
        } catch (Exception e) {

            result = new Result(e.getMessage(), false);

            return Response
                    .status(Status.BAD_REQUEST)
                    .entity(result)
                    .build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/esqueci-a-senha/{email}")
    public Response esqueciASenha(@PathParam("email") String email) throws NotFoundException {

        Result result;

        try {
            nitService.enviarEmailRedefinirSenha(email);

            return Response
                    .status(Status.CREATED)
                    .build();
        } catch (Exception e) {

            result = new Result(e.getMessage(), false);

            return Response
                    .status(Status.BAD_REQUEST)
                    .entity(result)
                    .build();
        }
    }

    @PATCH
    @Path("/redefinir-senha")
    @Authenticated
    public Response resetPassword(SenhaDTO senhaDTO) throws ParseException {

        String cnpj = jwt.getSubject();

        // System.out.println(jwtService.getUserId(tokenJson));

        nitService.redefinirSenha(cnpj, senhaDTO);

        return Response
                .status(Status.CREATED) // 204
                .build();
    }

    @GET
    @Path("/foto-perfil/download/{nomeImagem}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response download(@PathParam("nomeImagem") String nomeImagem) {

        ResponseBuilder response = Response.ok(nitFileService.download(nomeImagem));
        response.header("Content-Disposition", "attachment;filename="+nomeImagem);
        return response.build();
    }

    @PATCH
    @Path("/foto-perfil/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response salvarImagem(@MultipartForm NitImageForm form) {

        try {
            nitFileService.salvar(form.getId(), form.getNomeImagem(), form.getImagem());
            return Response.noContent().build();
        } catch (IOException e) {
            Result result = new Result(e.getMessage());
            return Response.status(Status.CONFLICT).entity(result).build();
        }
    }
}
