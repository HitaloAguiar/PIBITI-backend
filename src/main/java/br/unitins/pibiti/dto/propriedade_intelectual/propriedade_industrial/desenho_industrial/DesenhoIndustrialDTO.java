package br.unitins.pibiti.dto.propriedade_intelectual.propriedade_industrial.desenho_industrial;

import java.time.LocalDate;

public record DesenhoIndustrialDTO(
        Long idNit,
        String titulo,
        String titular,
        String descricao,
        LocalDate dataConcessao,
        String periodo,
        Long idTipoDesenhoIndustrial,
        Boolean visualizacaoPublica
) {
}
