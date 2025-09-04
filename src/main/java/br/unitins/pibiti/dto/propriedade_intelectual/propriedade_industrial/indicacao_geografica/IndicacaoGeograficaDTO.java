package br.unitins.pibiti.dto.propriedade_intelectual.propriedade_industrial.indicacao_geografica;

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
