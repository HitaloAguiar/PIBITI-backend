package br.unitins.pibiti.resource;

import java.util.List;

import org.eclipse.microprofile.jwt.JsonWebToken;

import br.unitins.pibiti.application.Result;
import br.unitins.pibiti.dto.propriedade_intelectual.propriedade_industrial.desenho_industrial.DesenhoIndustrialDTO;
import br.unitins.pibiti.dto.propriedade_intelectual.propriedade_industrial.desenho_industrial.DesenhoIndustrialResponseDTO;
import br.unitins.pibiti.service.propriedade_intelectual.propriedade_industrial.desenho_industrial.DesenhoIndustrialService;
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

@Path("/propriedades-intelectuais/desenhos-industriais")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DesenhoIndustrialResource {

    @Inject
    DesenhoIndustrialService desenhoIndustrialService;

    @Inject
    JsonWebToken jwt;

    @GET
    @Path("/{id}")
    public DesenhoIndustrialResponseDTO getDesenhoIndustrial(@PathParam("id") Long id) throws NotFoundException {
        return desenhoIndustrialService.getDesenhoIndustrial(id);
    }

    @GET
    public List<DesenhoIndustrialResponseDTO> getAllPublico(@QueryParam("page") @DefaultValue("0") int page,
                                                                        @QueryParam("pageSize") @DefaultValue("10") int pageSize,
                                                                        @QueryParam("isAscending") @DefaultValue("false") Boolean isAscending) {

        return desenhoIndustrialService.getAllPublico(page, pageSize, isAscending);
    }

    @GET
    @Path("/filtrado-por/{titulo}")
    public List<DesenhoIndustrialResponseDTO> getAllPublicoFiltradoPorTitulo(@PathParam("titulo") String titulo,
                                                                                @QueryParam("page") @DefaultValue("0") int page,
                                                                                @QueryParam("pageSize") @DefaultValue("10") int pageSize,
                                                                                @QueryParam("isAscending") @DefaultValue("false") Boolean isAscending) {

        return desenhoIndustrialService.getAllPublicoFiltradoPorTitulo(titulo, page, pageSize, isAscending);
    }

    @GET
    @Path("/nit/{id}")
    public List<DesenhoIndustrialResponseDTO> getAllByNit(@PathParam("id") Long idNit,
                                                                @QueryParam("page") @DefaultValue("0") int page,
                                                                @QueryParam("pageSize") @DefaultValue("10") int pageSize,
                                                                @QueryParam("isAscending") @DefaultValue("false") Boolean isAscending) {

        return desenhoIndustrialService.getAllByNit(idNit, page, pageSize, isAscending);
    }

    @GET
    @Path("/nit/{id}/filtrado-por/{titulo}")
    public List<DesenhoIndustrialResponseDTO> getAllByNitFiltradoPorTitulo(@PathParam("id") Long idNit,
                                                                                @PathParam("titulo") String titulo,
                                                                                @QueryParam("page") @DefaultValue("0") int page,
                                                                                @QueryParam("pageSize") @DefaultValue("10") int pageSize,
                                                                                @QueryParam("isAscending") @DefaultValue("false") Boolean isAscending) {

        return desenhoIndustrialService.getAllByNitFiltradoPorTitulo(idNit, titulo, page, pageSize, isAscending);
    }

    @POST
    @Authenticated
    public Response cadastrar(DesenhoIndustrialDTO desenhoIndustrialDTO) {

        Result result;

        try {

            return Response
                    .status(Response.Status.CREATED) // 201
                    .entity(desenhoIndustrialService.cadastrar(desenhoIndustrialDTO))
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
    public Response atualizar(@PathParam("id") Long id, DesenhoIndustrialDTO desenhoIndustrialDTO) {

        Result result;

        String cnpj = jwt.getSubject();

        try {

            desenhoIndustrialService.atualizar(cnpj, id, desenhoIndustrialDTO);

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
    public Response deletarDesenhoIndustrial(@PathParam("id") Long idDesenhoIndustrial) throws IllegalArgumentException, NotFoundException {

        Result result;

        String cnpj = jwt.getSubject();

        try {
            desenhoIndustrialService.deletarDesenhoIndustrial(cnpj, idDesenhoIndustrial);

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
