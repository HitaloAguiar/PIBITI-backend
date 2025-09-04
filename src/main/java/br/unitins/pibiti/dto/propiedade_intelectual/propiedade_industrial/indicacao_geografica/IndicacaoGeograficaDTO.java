package br.unitins.pibiti.dto.propiedade_intelectual.propiedade_industrial.indicacao_geografica;

import java.time.LocalDate;

public record IndicacaoGeograficaDTO(
        Long idNit,
        String titulo,
        String descricao,
        LocalDate dataConcessao,
        Long idEspecie,
        Long idNatureza,
        String tituloProdutoServico,
        String delimitacao,
        String requerente,
        Boolean visualizacaoPublica
) {
}
