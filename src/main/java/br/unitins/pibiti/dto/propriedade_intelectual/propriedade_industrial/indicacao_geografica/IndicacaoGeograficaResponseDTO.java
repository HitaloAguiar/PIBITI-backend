package br.unitins.pibiti.dto.propriedade_intelectual.propriedade_industrial.indicacao_geografica;

import br.unitins.pibiti.dto.nit.NitResponseDTO;
import br.unitins.pibiti.dto.propriedade_intelectual.TiposEnumResponseDTO;
import br.unitins.pibiti.model.IndicacaoGeografica;

import java.time.LocalDate;

public record IndicacaoGeograficaResponseDTO(
        Long idIndicacaoGeografica,
        TiposEnumResponseDTO tipoPropriedadeIntelectual,
        NitResponseDTO nit,
        String titulo,
        String descricao,
        LocalDate dataConcessao,
        TiposEnumResponseDTO especie,
        TiposEnumResponseDTO natureza,
        String tituloProdutoServico,
        String delimitacao,
        String requerente,
        Boolean visualizacaoPublica
) {
    public IndicacaoGeograficaResponseDTO(IndicacaoGeografica indicacaoGeografica) {
        this(indicacaoGeografica.getIdIndicacaoGeografica(), new TiposEnumResponseDTO(indicacaoGeografica.getTipoPropriedadeIntelectual().getId(), indicacaoGeografica.getTipoPropriedadeIntelectual().getLabel()), new NitResponseDTO(indicacaoGeografica.getNit()),
                indicacaoGeografica.getTitulo(), indicacaoGeografica.getDescricao(), indicacaoGeografica.getDataConcessao(), new TiposEnumResponseDTO(indicacaoGeografica.getEspecie().getId(), indicacaoGeografica.getEspecie().getLabel()),
                new TiposEnumResponseDTO(indicacaoGeografica.getNatureza().getId(), indicacaoGeografica.getNatureza().getLabel()), indicacaoGeografica.getTituloProdutoServico(),
                indicacaoGeografica.getDelimitacao(), indicacaoGeografica.getRequerente(), indicacaoGeografica.getVisualizacaoPublica());
    }
}
