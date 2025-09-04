package br.unitins.pibiti.dto.propriedade_intelectual.propriedade_industrial.marca;

import java.time.LocalDate;

public record MarcaDTO(
        Long idNit,
        String nome,
        String titular,
        LocalDate dataConcessao,
        String periodo,
        Long idNatureza,
        String classes,
        Boolean visualizacaoPublica
) {
}
