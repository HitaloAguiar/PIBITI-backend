package br.unitins.pibiti.dto.propiedade_intelectual.propiedade_industrial.desenho_industrial;

import br.unitins.pibiti.dto.nit.NitResponseDTO;
import br.unitins.pibiti.dto.propiedade_intelectual.TiposEnumResponseDTO;
import br.unitins.pibiti.model.DesenhoIndustrial;

import java.time.LocalDate;

public record DesenhoIndustrialResponseDTO(
        Long idDesenhoIndustrial,
        TiposEnumResponseDTO tipoPropiedadeIntelectual,
        NitResponseDTO nit,
        String titulo,
        String descricao,
        LocalDate dataConcessao,
        String periodo,
        TiposEnumResponseDTO tipoDesenhoIndustrial,
        Boolean visualizacaoPublica
) {
    public DesenhoIndustrialResponseDTO(DesenhoIndustrial desenhoIndustrial) {
        this(desenhoIndustrial.getIdDesenhoIndustrial(), new TiposEnumResponseDTO(desenhoIndustrial.getTipoPropiedadeIntelectual().getId(), desenhoIndustrial.getTipoPropiedadeIntelectual().getLabel()), new NitResponseDTO(desenhoIndustrial.getNit()),
                desenhoIndustrial.getTitulo(), desenhoIndustrial.getDescricao(), desenhoIndustrial.getDataConcessao(), desenhoIndustrial.getPeriodo(), new TiposEnumResponseDTO(desenhoIndustrial.getTipoDesenhoIndustrial().getId(), desenhoIndustrial.getTipoDesenhoIndustrial().getLabel()), desenhoIndustrial.getVisualizacaoPublica());
    }
}
