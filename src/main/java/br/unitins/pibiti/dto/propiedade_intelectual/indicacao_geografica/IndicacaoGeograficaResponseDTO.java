package br.unitins.pibiti.dto.propiedade_intelectual.indicacao_geografica;

import br.unitins.pibiti.dto.nit.NitResponseDTO;
import br.unitins.pibiti.dto.propiedade_intelectual.TiposEnumResponseDTO;
import br.unitins.pibiti.model.IndicacaoGeografica;

import java.time.LocalDate;

public record IndicacaoGeograficaResponseDTO(
        Long idPatente,
        TiposEnumResponseDTO tipoPropiedadeIntelectual,
        NitResponseDTO nit,
        String titulo,
        String descricao,
        LocalDate dataConcessao,
        TiposEnumResponseDTO especie,
        TiposEnumResponseDTO natureza,
        String tituloProdutoServico,
        String delimitacao,
        String requerente
) {
    public IndicacaoGeograficaResponseDTO(IndicacaoGeografica indicacaoGeografica) {
        this(indicacaoGeografica.getIdIndicacaoGeografica(), new TiposEnumResponseDTO(indicacaoGeografica.getTipoPropiedadeIntelectual().getId(), indicacaoGeografica.getTipoPropiedadeIntelectual().getLabel()), new NitResponseDTO(indicacaoGeografica.getNit()),
                indicacaoGeografica.getTitulo(), indicacaoGeografica.getDescricao(), indicacaoGeografica.getDataConcessao(), new TiposEnumResponseDTO(indicacaoGeografica.getEspecie().getId(), indicacaoGeografica.getEspecie().getLabel()),
                new TiposEnumResponseDTO(indicacaoGeografica.getNatureza().getId(), indicacaoGeografica.getNatureza().getLabel()), indicacaoGeografica.getTituloProdutoServico(),
                indicacaoGeografica.getDelimitacao(), indicacaoGeografica.getRequerente());
    }
}
