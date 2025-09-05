package br.unitins.pibiti.resource;

import java.util.List;

import org.eclipse.microprofile.jwt.JsonWebToken;

import br.unitins.pibiti.application.Result;
import br.unitins.pibiti.dto.propriedade_intelectual.propriedade_industrial.contato_franquia.ContratoFranquiaDTO;
import br.unitins.pibiti.dto.propriedade_intelectual.propriedade_industrial.contato_franquia.ContratoFranquiaResponseDTO;
import br.unitins.pibiti.service.propriedade_intelectual.propriedade_industrial.contrato_franquia.ContratoFranquiaService;
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

@Path("/propriedades-intelectuais/contratos-franquias")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ContratoFranquiaResource {

    @Inject
    ContratoFranquiaService contratoFranquiaService;

    @Inject
    JsonWebToken jwt;

    @GET
    @Path("/{id}")
    public ContratoFranquiaResponseDTO getContratoFranquia(@PathParam("id") Long id) throws NotFoundException {
        return contratoFranquiaService.getContratoFranquia(id);
    }

    @GET
    public List<ContratoFranquiaResponseDTO> getAllPublico(@QueryParam("page") @DefaultValue("0") int page,
                                                                        @QueryParam("pageSize") @DefaultValue("10") int pageSize,
                                                                        @QueryParam("isAscending") @DefaultValue("false") Boolean isAscending) {

        return contratoFranquiaService.getAllPublico(page, pageSize, isAscending);
    }

    @GET
    @Path("/filtrado-por/{titulo}")
    public List<ContratoFranquiaResponseDTO> getAllPublicoFiltradoPorTitulo(@PathParam("titulo") String titulo,
                                                                                @QueryParam("page") @DefaultValue("0") int page,
                                                                                @QueryParam("pageSize") @DefaultValue("10") int pageSize,
                                                                                @QueryParam("isAscending") @DefaultValue("false") Boolean isAscending) {

        return contratoFranquiaService.getAllPublicoFiltradoPorTitulo(titulo, page, pageSize, isAscending);
    }

    @GET
    @Path("/nit/{id}")
    public List<ContratoFranquiaResponseDTO> getAllByNit(@PathParam("id") Long idNit,
                                                                @QueryParam("page") @DefaultValue("0") int page,
                                                                @QueryParam("pageSize") @DefaultValue("10") int pageSize,
                                                                @QueryParam("isAscending") @DefaultValue("false") Boolean isAscending) {

        return contratoFranquiaService.getAllByNit(idNit, page, pageSize, isAscending);
    }

    @GET
    @Path("/nit/{id}/filtrado-por/{titulo}")
    public List<ContratoFranquiaResponseDTO> getAllByNitFiltradoPorTitulo(@PathParam("id") Long idNit,
                                                                                @PathParam("titulo") String titulo,
                                                                                @QueryParam("page") @DefaultValue("0") int page,
                                                                                @QueryParam("pageSize") @DefaultValue("10") int pageSize,
                                                                                @QueryParam("isAscending") @DefaultValue("false") Boolean isAscending) {

        return contratoFranquiaService.getAllByNitFiltradoPorTitulo(idNit, titulo, page, pageSize, isAscending);
    }

    @POST
    @Authenticated
    public Response cadastrar(ContratoFranquiaDTO contratoFranquiaDTO) {

        Result result;

        try {

            return Response
                    .status(Response.Status.CREATED) // 201
                    .entity(contratoFranquiaService.cadastrar(contratoFranquiaDTO))
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
    public Response atualizar(@PathParam("id") Long id, ContratoFranquiaDTO contratoFranquiaDTO) {

        Result result;

        String cnpj = jwt.getSubject();

        try {

            contratoFranquiaService.atualizar(cnpj, id, contratoFranquiaDTO);

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
    public Response deletarContratoFranquia(@PathParam("id") Long idContratoFranquia) throws IllegalArgumentException, NotFoundException {

        Result result;

        String cnpj = jwt.getSubject();

        try {
            contratoFranquiaService.deletarContratoFranquia(cnpj, idContratoFranquia);

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
