package br.unitins.pibiti.service.avaliacao_maturidade;

import java.util.List;

import br.unitins.pibiti.dto.avaliacao_maturidade.AvaliacaoMaturidadeDTO;
import br.unitins.pibiti.dto.avaliacao_maturidade.AvaliacaoMaturidadeGraficoResponseDTO;
import br.unitins.pibiti.dto.avaliacao_maturidade.AvaliacaoMaturidadeResponseDTO;
import br.unitins.pibiti.dto.variavel.VariavelResponseDTO;

public interface AvaliacaoMaturidadeService {
    
    List<VariavelResponseDTO> getVariaveis();

    AvaliacaoMaturidadeResponseDTO cadastrarAvaliacaoMaturidade(AvaliacaoMaturidadeDTO avaliacaoMaturidadeDTO);

    AvaliacaoMaturidadeResponseDTO getLastAvaliacaoMaturidade(Long idNit);

    List<AvaliacaoMaturidadeGraficoResponseDTO> getDadosGrafico(Long idNit);

    List<AvaliacaoMaturidadeResponseDTO> getHistoricoAvaliacoes(Long idNit);

    // Essas duas rotas serão para eventualmente fazer a funcionalidade de exportar o resultado da avaliação
    byte[] criarRelatorioAvaliacao();

    byte[] gerarPdf();
}
