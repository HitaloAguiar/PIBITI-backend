package br.unitins.pibiti.resource;

import br.unitins.pibiti.application.Result;
import br.unitins.pibiti.dto.propiedade_intelectual.desenho_industrial.DesenhoIndustrialDTO;
import br.unitins.pibiti.dto.propiedade_intelectual.desenho_industrial.DesenhoIndustrialResponseDTO;
import br.unitins.pibiti.service.propiedade_intelectual.desenho_industrial.DesenhoIndustrialService;
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

@Path("/propiedades-intelectuais/desenhos-industriais")
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
