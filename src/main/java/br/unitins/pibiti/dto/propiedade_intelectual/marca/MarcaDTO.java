package br.unitins.pibiti.dto.propiedade_intelectual.marca;

import java.time.LocalDate;

public record MarcaDTO (
        Long idNit,
        String nome,
        String titular,
        LocalDate dataConcessao,
        String periodo,
        String natureza,
        String classes
) {
}
