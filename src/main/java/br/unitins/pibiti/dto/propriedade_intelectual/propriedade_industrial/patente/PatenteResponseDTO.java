package br.unitins.pibiti.dto.propriedade_intelectual.propriedade_industrial.patente;

import br.unitins.pibiti.dto.nit.NitResponseDTO;
import br.unitins.pibiti.dto.propriedade_intelectual.TiposEnumResponseDTO;
import br.unitins.pibiti.model.Patente;

import java.time.LocalDate;

public record PatenteResponseDTO(
        Long idPatente,
        TiposEnumResponseDTO tipoPropriedadeIntelectual,
        NitResponseDTO nit,
        String titulo,
        String resumo,
        LocalDate dataConcessao,
        String periodo,
        TiposEnumResponseDTO tipoPatente,
        String classificacao,
        String categorias,
        Boolean visualizacaoPublica
) {
    public PatenteResponseDTO(Patente patente) {
        this(patente.getIdPatente(), new TiposEnumResponseDTO(patente.getTipoPropriedadeIntelectual().getId(), patente.getTipoPropriedadeIntelectual().getLabel()), new NitResponseDTO(patente.getNit()),
                patente.getTitulo(), patente.getResumo(), patente.getDataConcessao(), patente.getPeriodo(), new TiposEnumResponseDTO(patente.getTipo().getId(), patente.getTipo().getLabel()),
                patente.getClassificacao(), patente.getCategorias(), patente.getVisualizacaoPublica());
    }
}
