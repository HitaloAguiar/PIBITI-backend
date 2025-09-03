package br.unitins.pibiti.dto.propiedade_intelectual.patente;

import java.time.LocalDate;

public record PatenteDTO(
        Long idNit,
        String titulo,
        String resumo,
        LocalDate dataConcessao,
        String periodo,
        Long idTipoPatente,
        String classificacao,
        String categorias
) {
}
