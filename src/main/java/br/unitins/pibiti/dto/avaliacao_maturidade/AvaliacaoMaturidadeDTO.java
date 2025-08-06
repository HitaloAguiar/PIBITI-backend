package br.unitins.pibiti.dto.avaliacao_maturidade;

import java.util.List;

public record AvaliacaoMaturidadeDTO(
    List<Long> servicosFornecidos,
    List<VariavelAvaliacaoDTO> variaveis
) {
    
}
