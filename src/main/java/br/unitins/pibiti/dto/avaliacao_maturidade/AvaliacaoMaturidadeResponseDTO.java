package br.unitins.pibiti.dto.avaliacao_maturidade;

import java.util.Map;

import br.unitins.pibiti.dto.nit.NitResponseDTO;

public record AvaliacaoMaturidadeResponseDTO(
    Long id,
    Double img,
    Map<String, Double> imds,
    NitResponseDTO nit
) {
    
}
