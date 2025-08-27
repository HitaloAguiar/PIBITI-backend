package br.unitins.pibiti.service.avaliacao_maturidade;

import java.util.List;

import br.unitins.pibiti.dto.avaliacao_maturidade.AvaliacaoMaturidadeDTO;
import br.unitins.pibiti.dto.avaliacao_maturidade.AvaliacaoMaturidadeGraficoResponseDTO;
import br.unitins.pibiti.dto.avaliacao_maturidade.AvaliacaoMaturidadeResponseDTO;
import br.unitins.pibiti.dto.variavel.VariavelResponseDTO;

public interface AvaliacaoMaturidadeService {
    
    List<VariavelResponseDTO> getVariaveis();

    AvaliacaoMaturidadeResponseDTO cadastrarAvaliacaoMaturidade(AvaliacaoMaturidadeDTO avaliacaoMaturidadeDTO);

    AvaliacaoMaturidadeResponseDTO atualizarAvaliacaoMaturidade(Long idAvaliacao, AvaliacaoMaturidadeDTO avaliacaoMaturidadeDTO);

    void deletarAvaliacaoMaturidade(Long idAvaliacao);

    AvaliacaoMaturidadeResponseDTO getLastAvaliacaoMaturidade(Long idNit);

    List<AvaliacaoMaturidadeGraficoResponseDTO> getDadosGrafico(Long idNit);

    List<AvaliacaoMaturidadeResponseDTO> getHistoricoAvaliacoes(Long idNit, int page, int pageSize, Boolean isAscending);
    
    List<AvaliacaoMaturidadeResponseDTO> getHistoricoAvaliacoesByNivelMaturidade(Long idNit, String nivelMaturidade, int page, int pageSize, Boolean isAscending);

    AvaliacaoMaturidadeResponseDTO getAvaliacaoMaturidade(Long idAvaliacao);

    // Essas duas rotas serão para eventualmente fazer a funcionalidade de exportar o resultado da avaliação
    byte[] criarRelatorioAvaliacao();

    byte[] gerarPdf();
}
