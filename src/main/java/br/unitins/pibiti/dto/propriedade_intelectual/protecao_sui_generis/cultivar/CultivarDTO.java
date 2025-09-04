package br.unitins.pibiti.dto.propriedade_intelectual.protecao_sui_generis.cultivar;

public record CultivarDTO(
        Long idNit,
        String titulo,
        String descricao,
        Long idCategoria,
        Boolean visualizacaoPublica
) {
}
