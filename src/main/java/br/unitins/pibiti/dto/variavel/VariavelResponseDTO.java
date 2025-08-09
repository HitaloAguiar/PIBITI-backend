package br.unitins.pibiti.dto.variavel;

import br.unitins.pibiti.model.Variavel;

// import br.unitins.pibiti.dto.dimensao.DimensaoResponseDTO;

public record VariavelResponseDTO(
    Long idVariavel,
    String nome,
    // Double peso,
    String nomeDimensao
) {
   
    public VariavelResponseDTO (Variavel variavel) {

        this(variavel.getIdVariavelMaturidadeNIT(), variavel.getNome(), variavel.getDimensao().getNome());
    }
}
