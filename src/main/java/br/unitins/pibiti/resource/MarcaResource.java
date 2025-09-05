package br.unitins.pibiti.resource;

import java.util.List;

import org.eclipse.microprofile.jwt.JsonWebToken;

import br.unitins.pibiti.application.Result;
import br.unitins.pibiti.dto.propriedade_intelectual.propriedade_industrial.marca.MarcaDTO;
import br.unitins.pibiti.dto.propriedade_intelectual.propriedade_industrial.marca.MarcaResponseDTO;
import br.unitins.pibiti.service.propriedade_intelectual.propriedade_industrial.marca.MarcaService;
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

@Path("/propriedades-intelectuais/marcas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MarcaResource {

    @Inject
    MarcaService marcaService;

    @Inject
    JsonWebToken jwt;

    @GET
    @Path("/{id}")
    public MarcaResponseDTO getMarca(@PathParam("id") Long id) throws NotFoundException {
        return marcaService.getMarca(id);
    }

    @GET
    public List<MarcaResponseDTO> getAllPublico(@QueryParam("page") @DefaultValue("0") int page,
                                                                        @QueryParam("pageSize") @DefaultValue("10") int pageSize,
                                                                        @QueryParam("isAscending") @DefaultValue("false") Boolean isAscending) {

        return marcaService.getAllPublico(page, pageSize, isAscending);
    }

    @GET
    @Path("/filtrado-por/{nome}")
    public List<MarcaResponseDTO> getAllPublicoFiltradoPorNome(@PathParam("nome") String nome,
                                                                                @QueryParam("page") @DefaultValue("0") int page,
                                                                                @QueryParam("pageSize") @DefaultValue("10") int pageSize,
                                                                                @QueryParam("isAscending") @DefaultValue("false") Boolean isAscending) {

        return marcaService.getAllPublicoFiltradoPorNome(nome, page, pageSize, isAscending);
    }

    @GET
    @Path("/nit/{id}")
    public List<MarcaResponseDTO> getAllByNit(@PathParam("id") Long idNit,
                                                                @QueryParam("page") @DefaultValue("0") int page,
                                                                @QueryParam("pageSize") @DefaultValue("10") int pageSize,
                                                                @QueryParam("isAscending") @DefaultValue("false") Boolean isAscending) {

        return marcaService.getAllByNit(idNit, page, pageSize, isAscending);
    }

    @GET
    @Path("/nit/{id}/filtrado-por/{nome}")
    public List<MarcaResponseDTO> getAllByNitFiltradoPorNome(@PathParam("id") Long idNit,
                                                                                @PathParam("nome") String nome,
                                                                                @QueryParam("page") @DefaultValue("0") int page,
                                                                                @QueryParam("pageSize") @DefaultValue("10") int pageSize,
                                                                                @QueryParam("isAscending") @DefaultValue("false") Boolean isAscending) {

        return marcaService.getAllByNitFiltradoPorNome(idNit, nome, page, pageSize, isAscending);
    }

    @POST
    @Authenticated
    public Response cadastrar(MarcaDTO marcaDTO) {

        Result result;

        try {

            return Response
                    .status(Response.Status.CREATED) // 201
                    .entity(marcaService.cadastrar(marcaDTO))
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
    public Response atualizar(@PathParam("id") Long id, MarcaDTO marcaDTO) {

        Result result;

        String cnpj = jwt.getSubject();

        try {

            marcaService.atualizar(cnpj, id, marcaDTO);

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
    public Response deletarMarca(@PathParam("id") Long idMarca) throws IllegalArgumentException, NotFoundException {

        Result result;

        String cnpj = jwt.getSubject();

        try {
            marcaService.deletarMarca(cnpj, idMarca);

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
