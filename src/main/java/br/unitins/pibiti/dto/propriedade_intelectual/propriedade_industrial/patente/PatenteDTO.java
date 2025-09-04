package br.unitins.pibiti.dto.propriedade_intelectual.propriedade_industrial.patente;

import java.time.LocalDate;

public record PatenteDTO(
        Long idNit,
        String titulo,
        String resumo,
        LocalDate dataConcessao,
        String periodo,
        Long idTipoPatente,
        String classificacao,
        String categorias,
        Boolean visualizacaoPublica
) {
}
