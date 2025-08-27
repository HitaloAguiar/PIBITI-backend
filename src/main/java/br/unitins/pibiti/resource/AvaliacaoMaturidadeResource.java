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
    public AvaliacaoMaturidadeResponseDTO getLastAvaliacaoMaturidade(@PathParam("id") Long idNit) throws NotFoundException {

        return avaliacaoMaturidadeService.getLastAvaliacaoMaturidade(idNit);
    }

    @GET
    @Path("/dados-grafico/{id}")
    @Authenticated
    public List<AvaliacaoMaturidadeGraficoResponseDTO> getDadosGrafico(@PathParam("id") Long idNit) throws NotFoundException {

        return avaliacaoMaturidadeService.getDadosGrafico(idNit);
    }

    @GET
    @Path("/historico-avaliacoes/{id}")
    @Authenticated
    public List<AvaliacaoMaturidadeResponseDTO> getHistoricoAvaliacoes(@PathParam("id") Long idNit,
                                                                        @QueryParam("page") @DefaultValue("0") int page,
                                                                        @QueryParam("pageSize") @DefaultValue("10") int pageSize,
                                                                        @QueryParam("isAscending") @DefaultValue("false") Boolean isAscending) throws NotFoundException {

        return avaliacaoMaturidadeService.getHistoricoAvaliacoes(idNit, page, pageSize, isAscending);
    }

    @GET
    @Path("/historico-avaliacoes-com-filtro/{id}/{nivel_maturidade}")
    @Authenticated
    public List<AvaliacaoMaturidadeResponseDTO> getHistoricoAvaliacoesComFiltro(@PathParam("id") Long idNit,
                                                                                @PathParam("nivel_maturidade") String nivelMaturidade,
                                                                                @QueryParam("page") @DefaultValue("0") int page,
                                                                                @QueryParam("pageSize") @DefaultValue("10") int pageSize,
                                                                                @QueryParam("isAscending") @DefaultValue("false") Boolean isAscending) throws NotFoundException {

        return avaliacaoMaturidadeService.getHistoricoAvaliacoesByNivelMaturidade(idNit, nivelMaturidade, page, pageSize, isAscending);
    }

    @GET
    @Path("/avaliacao-maturidade/{id}")
    @Authenticated
    public AvaliacaoMaturidadeResponseDTO getAvaliacaoMaturidade(@PathParam("id") Long id) throws NotFoundException {
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

    @PUT
    @Path("/atualizar-avaliacao-maturidade/{id}")
    @Authenticated
    public Response atualizarAvaliacao(@PathParam("id") Long idAvaliacao, AvaliacaoMaturidadeDTO avaliacaoMaturidadeDTO) {

        Result result;

        try {

            return Response
                    .status(Status.CREATED) // 201
                    .entity(avaliacaoMaturidadeService.atualizarAvaliacaoMaturidade(idAvaliacao, avaliacaoMaturidadeDTO))
                    .build();
        } catch (Exception e) {

            result = new Result(e.getMessage(), false);

            return Response
                    .status(Status.BAD_REQUEST)
                    .entity(result)
                    .build();
        }
    }

    @DELETE
    @Path("/deletar-avaliacao-maturidade/{id}")
    @Authenticated
    public Response deletarAvaliacao(@PathParam("id") Long idAvaliacao) throws IllegalArgumentException, NotFoundException {

        Result result;

        try {
            avaliacaoMaturidadeService.deletarAvaliacaoMaturidade(idAvaliacao);

            return Response
                    .status(Status.NO_CONTENT)
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
