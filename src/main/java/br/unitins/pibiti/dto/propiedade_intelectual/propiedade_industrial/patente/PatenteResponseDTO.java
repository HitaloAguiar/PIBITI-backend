package br.unitins.pibiti.dto.propiedade_intelectual.propiedade_industrial.patente;

import br.unitins.pibiti.dto.nit.NitResponseDTO;
import br.unitins.pibiti.dto.propiedade_intelectual.TiposEnumResponseDTO;
import br.unitins.pibiti.model.Patente;

import java.time.LocalDate;

public record PatenteResponseDTO(
        Long idPatente,
        TiposEnumResponseDTO tipoPropiedadeIntelectual,
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
        this(patente.getIdPatente(), new TiposEnumResponseDTO(patente.getTipoPropiedadeIntelectual().getId(), patente.getTipoPropiedadeIntelectual().getLabel()), new NitResponseDTO(patente.getNit()),
                patente.getTitulo(), patente.getResumo(), patente.getDataConcessao(), patente.getPeriodo(), new TiposEnumResponseDTO(patente.getTipo().getId(), patente.getTipo().getLabel()),
                patente.getClassificacao(), patente.getCategorias(), patente.getVisualizacaoPublica());
    }
}
