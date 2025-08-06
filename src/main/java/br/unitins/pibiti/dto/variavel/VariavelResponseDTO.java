package br.unitins.pibiti.dto.variavel;

import br.unitins.pibiti.dto.dimensao.DimensaoResponseDTO;

public record VariavelResponseDTO(
    Long idVariavel,
    String nome,
    Double peso,
    DimensaoResponseDTO dimensaoResponseDTO
) {
    
}
