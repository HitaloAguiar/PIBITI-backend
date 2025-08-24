package br.unitins.pibiti.service.avaliacao_maturidade;

import java.util.List;

import br.unitins.pibiti.dto.avaliacao_maturidade.AvaliacaoMaturidadeDTO;
import br.unitins.pibiti.dto.avaliacao_maturidade.AvaliacaoMaturidadeResponseDTO;
import br.unitins.pibiti.dto.variavel.VariavelResponseDTO;

public interface AvaliacaoMaturidadeService {
    
    List<VariavelResponseDTO> getVariaveis();

    AvaliacaoMaturidadeResponseDTO cadastrarAvaliacaoMaturidade(AvaliacaoMaturidadeDTO avaliacaoMaturidadeDTO);
}
