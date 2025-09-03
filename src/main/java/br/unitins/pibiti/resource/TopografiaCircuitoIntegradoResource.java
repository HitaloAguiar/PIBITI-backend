package br.unitins.pibiti.resource;

import br.unitins.pibiti.application.Result;
import br.unitins.pibiti.dto.propiedade_intelectual.protecao_sui_generis.topografia_circuito_integrado.TopografiaCircuitoIntegradoDTO;
import br.unitins.pibiti.dto.propiedade_intelectual.protecao_sui_generis.topografia_circuito_integrado.TopografiaCircuitoIntegradoResponseDTO;
import br.unitins.pibiti.service.propiedade_intelectual.protecao_sui_generis.topografia_circuito_integrado.TopografiaCircuitoIntegradoService;
import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;

@Path("/propiedades-intelectuais/topografias-circuitos-integrados")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TopografiaCircuitoIntegradoResource {

    @Inject
    TopografiaCircuitoIntegradoService topografiaCircuitoIntegradoService;

    @Inject
    JsonWebToken jwt;

    @GET
    @Path("/{id}")
    public TopografiaCircuitoIntegradoResponseDTO getTopografiaCircuitoIntegrado(@PathParam("id") Long id) throws NotFoundException {
        return topografiaCircuitoIntegradoService.getTopografiaCircuitoIntegrado(id);
    }

    @POST
    @Authenticated
    public Response cadastrar(TopografiaCircuitoIntegradoDTO topografiaCircuitoIntegradoDTO) {

        Result result;

        try {

            return Response
                    .status(Response.Status.CREATED) // 201
                    .entity(topografiaCircuitoIntegradoService.cadastrar(topografiaCircuitoIntegradoDTO))
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
    public Response atualizar(@PathParam("id") Long id, TopografiaCircuitoIntegradoDTO topografiaCircuitoIntegradoDTO) {

        Result result;

        String cnpj = jwt.getSubject();

        try {

            topografiaCircuitoIntegradoService.atualizar(cnpj, id, topografiaCircuitoIntegradoDTO);

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
    public Response deletarTopografiaCircuitoIntegrado(@PathParam("id") Long idTopografiaCircuitoIntegrado) throws IllegalArgumentException, NotFoundException {

        Result result;

        String cnpj = jwt.getSubject();

        try {
            topografiaCircuitoIntegradoService.deletarTopografiaCircuitoIntegrado(cnpj, idTopografiaCircuitoIntegrado);

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
