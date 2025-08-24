package br.unitins.pibiti.service.avaliacao_maturidade;

import java.util.List;

import br.unitins.pibiti.dto.avaliacao_maturidade.AvaliacaoMaturidadeDTO;
import br.unitins.pibiti.dto.avaliacao_maturidade.AvaliacaoMaturidadeResponseDTO;
import br.unitins.pibiti.dto.variavel.VariavelResponseDTO;

public interface AvaliacaoMaturidadeService {
    
    List<VariavelResponseDTO> getVariaveis();

    AvaliacaoMaturidadeResponseDTO cadastrarAvaliacaoMaturidade(AvaliacaoMaturidadeDTO avaliacaoMaturidadeDTO);

    // Essas duas rotas serão para eventualmente fazer a funcionalidade de exportar o resultado da avaliação
    byte[] criarRelatorioAvaliacao();

    byte[] gerarPdf();
}
