package br.unitins.pibiti.resource;

import br.unitins.pibiti.application.Result;
import br.unitins.pibiti.dto.propiedade_intelectual.protecao_sui_generis.cultivar.CultivarDTO;
import br.unitins.pibiti.dto.propiedade_intelectual.protecao_sui_generis.cultivar.CultivarResponseDTO;
import br.unitins.pibiti.service.propiedade_intelectual.protecao_sui_generis.cultivar.CultivarService;
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

@Path("/propiedades-intelectuais/cultivares")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CultivarResource {

    @Inject
    CultivarService cultivarService;

    @Inject
    JsonWebToken jwt;

    @GET
    @Path("/{id}")
    public CultivarResponseDTO getCultivar(@PathParam("id") Long id) throws NotFoundException {
        return cultivarService.getCultivar(id);
    }

    @POST
    @Authenticated
    public Response cadastrar(CultivarDTO cultivarDTO) {

        Result result;

        try {

            return Response
                    .status(Response.Status.CREATED) // 201
                    .entity(cultivarService.cadastrar(cultivarDTO))
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
    public Response atualizar(@PathParam("id") Long id, CultivarDTO cultivarDTO) {

        Result result;

        String cnpj = jwt.getSubject();

        try {

            cultivarService.atualizar(cnpj, id, cultivarDTO);

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
    public Response deletarCultivar(@PathParam("id") Long idCultivar) throws IllegalArgumentException, NotFoundException {

        Result result;

        String cnpj = jwt.getSubject();

        try {
            cultivarService.deletarCultivar(cnpj, idCultivar);

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
