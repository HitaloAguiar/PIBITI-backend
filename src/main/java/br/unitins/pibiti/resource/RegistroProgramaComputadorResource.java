package br.unitins.pibiti.resource;

import br.unitins.pibiti.application.Result;
import br.unitins.pibiti.dto.propriedade_intelectual.direito_autoral.registro_programa_computador.RegistroProgramaComputadorDTO;
import br.unitins.pibiti.dto.propriedade_intelectual.direito_autoral.registro_programa_computador.RegistroProgramaComputadorResponseDTO;
import br.unitins.pibiti.service.propriedade_intelectual.direito_autoral.registro_programa_computador.RegistroProgramaComputadorService;
import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

import org.eclipse.microprofile.jwt.JsonWebToken;

@Path("/propriedades-intelectuais/registros-programa-computador")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RegistroProgramaComputadorResource {

    @Inject
    RegistroProgramaComputadorService registroProgramaService;

    @Inject
    JsonWebToken jwt;

    @GET
    @Path("/{id}")
    public RegistroProgramaComputadorResponseDTO getRegistroProgramaComputador(@PathParam("id") Long id) throws NotFoundException {
        return registroProgramaService.getRegistroProgramaComputador(id);
    }

    @GET
    @Path("/nit/{id}")
    public List<RegistroProgramaComputadorResponseDTO> getAll(@PathParam("id") Long idNit,
                                                                @QueryParam("page") @DefaultValue("0") int page,
                                                                @QueryParam("pageSize") @DefaultValue("10") int pageSize) {

        return registroProgramaService.getAllRegistroProgramaComputador(idNit, page, pageSize);
    }

    @GET
    @Path("/nit/{id}/filtrado-por/{titulo}")
    public List<RegistroProgramaComputadorResponseDTO> getAllFiltradoPorTitulo(@PathParam("id") Long idNit,
                                                                                @PathParam("titulo") String titulo,
                                                                                @QueryParam("page") @DefaultValue("0") int page,
                                                                                @QueryParam("pageSize") @DefaultValue("10") int pageSize) {

        return registroProgramaService.getAllFiltradoPorTitulo(idNit, titulo, page, pageSize);
    }

    @POST
    @Authenticated
    public Response cadastrar(RegistroProgramaComputadorDTO registroProgramaDTO) {

        Result result;

        try {

            return Response
                    .status(Response.Status.CREATED) // 201
                    .entity(registroProgramaService.cadastrar(registroProgramaDTO))
                    .build();
        } catch (ConstraintViolationException e) {

            result = new Result(e.getConstraintViolations());

            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(result)
                    .build();
        } catch (Exception e) {

            result = new Result(e.getMessage(), false);

            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(result)
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    @Authenticated
    public Response atualizar(@PathParam("id") Long id, RegistroProgramaComputadorDTO registroProgramaDTO) {

        Result result;

        String cnpj = jwt.getSubject();

        try {

            registroProgramaService.atualizar(cnpj, id, registroProgramaDTO);

            return Response
                    .status(Response.Status.NO_CONTENT) // 204
                    .build();
        } catch (ConstraintViolationException e) {

            result = new Result(e.getConstraintViolations());

            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(result)
                    .build();
        } catch (Exception e) {

            result = new Result(e.getMessage(), false);

            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(result)
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Authenticated
    public Response deletarRegistroProgramaComputador(@PathParam("id") Long idRegistroProgramaComputador) throws IllegalArgumentException, NotFoundException {

        Result result;

        String cnpj = jwt.getSubject();

        try {
            registroProgramaService.deletarRegistroProgramaComputador(cnpj, idRegistroProgramaComputador);

            return Response
                    .status(Response.Status.NO_CONTENT)
                    .build();
        } catch (Exception e) {

            result = new Result(e.getMessage(), false);

            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(result)
                    .build();
        }
    }

}
