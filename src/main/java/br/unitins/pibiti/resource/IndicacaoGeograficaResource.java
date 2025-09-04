package br.unitins.pibiti.resource;

import br.unitins.pibiti.application.Result;
import br.unitins.pibiti.dto.propiedade_intelectual.propiedade_industrial.indicacao_geografica.IndicacaoGeograficaDTO;
import br.unitins.pibiti.dto.propiedade_intelectual.propiedade_industrial.indicacao_geografica.IndicacaoGeograficaResponseDTO;
import br.unitins.pibiti.service.propiedade_intelectual.propiedade_industrial.indicacao_geografica.IndicacaoGeograficaService;
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

@Path("/propiedades-intelectuais/indicacoes-geograficas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class IndicacaoGeograficaResource {

    @Inject
    IndicacaoGeograficaService indicacaoGeograficaService;

    @Inject
    JsonWebToken jwt;

    @GET
    @Path("/{id}")
    public IndicacaoGeograficaResponseDTO getIndicacaoGeografica(@PathParam("id") Long id) throws NotFoundException {
        return indicacaoGeograficaService.getIndicacaoGeografica(id);
    }

    @GET
    @Path("/nit/{id}")
    public List<IndicacaoGeograficaResponseDTO> getAll(@PathParam("id") Long idNit,
                                                                @QueryParam("page") @DefaultValue("0") int page,
                                                                @QueryParam("pageSize") @DefaultValue("10") int pageSize) {

        return indicacaoGeograficaService.getAllIndicacaoGeografica(idNit, page, pageSize);
    }

    @GET
    @Path("/nit/{id}/filtrado-por/{titulo}")
    public List<IndicacaoGeograficaResponseDTO> getAllFiltradoPorTitulo(@PathParam("id") Long idNit,
                                                                                @PathParam("titulo") String titulo,
                                                                                @QueryParam("page") @DefaultValue("0") int page,
                                                                                @QueryParam("pageSize") @DefaultValue("10") int pageSize) {

        return indicacaoGeograficaService.getAllFiltradoPorTitulo(idNit, titulo, page, pageSize);
    }

    @POST
    @Authenticated
    public Response cadastrar(IndicacaoGeograficaDTO indicacaoGeograficaDTO) {

        Result result;

        try {

            return Response
                    .status(Response.Status.CREATED) // 201
                    .entity(indicacaoGeograficaService.cadastrar(indicacaoGeograficaDTO))
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
    public Response atualizar(@PathParam("id") Long id, IndicacaoGeograficaDTO indicacaoGeograficaDTO) {

        Result result;

        String cnpj = jwt.getSubject();

        try {

            indicacaoGeograficaService.atualizar(cnpj, id, indicacaoGeograficaDTO);

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
    public Response deletarIndicacaoGeografica(@PathParam("id") Long idIndicacaoGeografica) throws IllegalArgumentException, NotFoundException {

        Result result;

        String cnpj = jwt.getSubject();

        try {
            indicacaoGeograficaService.deletarIndicacaoGeografica(cnpj, idIndicacaoGeografica);

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
