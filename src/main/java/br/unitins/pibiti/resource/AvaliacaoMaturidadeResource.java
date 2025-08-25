package br.unitins.pibiti.resource;

import java.util.List;

import br.unitins.pibiti.application.Result;
import br.unitins.pibiti.dto.avaliacao_maturidade.AvaliacaoMaturidadeDTO;
import br.unitins.pibiti.dto.avaliacao_maturidade.AvaliacaoMaturidadeGraficoResponseDTO;
import br.unitins.pibiti.dto.avaliacao_maturidade.AvaliacaoMaturidadeResponseDTO;
import br.unitins.pibiti.dto.variavel.VariavelResponseDTO;
import br.unitins.pibiti.service.avaliacao_maturidade.AvaliacaoMaturidadeService;
import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/nits")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AvaliacaoMaturidadeResource {
    
    @Inject
    AvaliacaoMaturidadeService avaliacaoMaturidadeService;

    @GET
    @Path("/variaveis")
    @Authenticated
    public List<VariavelResponseDTO> getVariaveis() {
        
        return avaliacaoMaturidadeService.getVariaveis();
    }

    @GET
    @Path("/last-avaliacao-maturidade/{id}")
    @Authenticated
    public AvaliacaoMaturidadeResponseDTO getLastAvaliacaoMaturidade(@PathParam("id") Long id) {

        return avaliacaoMaturidadeService.getLastAvaliacaoMaturidade(id);
    }

    @GET
    @Path("/dados-grafico/{id}")
    @Authenticated
    public List<AvaliacaoMaturidadeGraficoResponseDTO> getDadosGrafico(@PathParam("id") Long id) {

        return avaliacaoMaturidadeService.getDadosGrafico(id);
    }

    @GET
    @Path("/historico-avaliacoes/{id}")
    @Authenticated
    public List<AvaliacaoMaturidadeResponseDTO> getHistoricoAvaliacoes(@PathParam("id") Long id) {

        return avaliacaoMaturidadeService.getHistoricoAvaliacoes(id);
    }

    @GET
    @Path("/avaliacao-maturidade/{id}")
    @Authenticated
    public AvaliacaoMaturidadeResponseDTO geAvaliacaoMaturidade(@PathParam("id") Long id) {
        return avaliacaoMaturidadeService.getAvaliacaoMaturidade(id);
    }

    @POST
    @Path("/cadastrar-avaliacao-maturidade")
    @Authenticated
    public Response cadastrarAvaliacao(AvaliacaoMaturidadeDTO avaliacaoMaturidadeDTO) {

        Result result;

        try {

            return Response
                    .status(Status.CREATED) // 201
                    .entity(avaliacaoMaturidadeService.cadastrarAvaliacaoMaturidade(avaliacaoMaturidadeDTO))
                    .build();
        } catch (Exception e) {

            result = new Result(e.getMessage(), false);

            return Response
                    .status(Status.BAD_REQUEST)
                    .entity(result)
                    .build();
        }
    }
}
