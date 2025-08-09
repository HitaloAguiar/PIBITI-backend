package br.unitins.pibiti.dto.avaliacao_maturidade;

import java.util.List;

public record AvaliacaoMaturidadeDTO(
    Long idNit,
    List<Long> servicosFornecidos,
    List<VariavelAvaliacaoDTO> variaveis
) {
    
}
