package br.unitins.pibiti.resource;

import br.unitins.pibiti.application.Result;
import br.unitins.pibiti.dto.avaliacao_trl_pi.AvaliacaoTrlPiDTO;
import br.unitins.pibiti.dto.avaliacao_trl_pi.AvaliacaoTrlPiResponseDTO;
import br.unitins.pibiti.service.avaliacao_trl_pi.AvaliacaoTrlPropriedadeIntelectualService;
import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import org.eclipse.microprofile.jwt.JsonWebToken;

@Path("/propriedades-intelectuais/avaliacao-trl")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AvaliacaoTrlPropriedadeIntelectualResource {

    @Inject
    AvaliacaoTrlPropriedadeIntelectualService avaliacaoTrlPropriedadeIntelectualService;

    @Inject
    JsonWebToken jwt;

    @GET
    @Path("/{id}")
    @Authenticated
    public AvaliacaoTrlPiResponseDTO getAvaliacao(@PathParam("id") Long idAvaliacao) {
        return avaliacaoTrlPropriedadeIntelectualService.getAvaliacao(idAvaliacao);
    }

    @POST
    @Authenticated
    public Response cadastrarAvaliacao(AvaliacaoTrlPiDTO avaliacaoDTO) {

        Result result;

        try {

            return Response.status(Status.CREATED) // 201
                    .entity(avaliacaoTrlPropriedadeIntelectualService.cadastrar(avaliacaoDTO)).build();
        } catch (Exception e) {

            result = new Result(e.getMessage(), false);

            return Response.status(Status.BAD_REQUEST).entity(result).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Authenticated
    public Response deletarAvaliacao(@PathParam("id") Long idAvaliacao) throws IllegalArgumentException, NotFoundException {

        Result result;

        String cnpj = jwt.getSubject();

        try {
            avaliacaoTrlPropriedadeIntelectualService.deletarAvaliacao(cnpj, idAvaliacao);

            return Response.status(Status.NO_CONTENT).build();
        } catch (Exception e) {

            result = new Result(e.getMessage(), false);

            return Response.status(Status.BAD_REQUEST).entity(result).build();
        }
    }

}
