package br.unitins.pibiti.dto.propiedade_intelectual.propiedade_industrial.contato_franquia;

import br.unitins.pibiti.dto.nit.NitResponseDTO;
import br.unitins.pibiti.dto.propiedade_intelectual.TiposEnumResponseDTO;
import br.unitins.pibiti.model.ContratoFranquia;

public record ContratoFranquiaResponseDTO(
        Long idContratoFranquia,
        TiposEnumResponseDTO tipoPropiedadeIntelectual,
        NitResponseDTO nit,
        String titulo,
        String descricao
) {
    public ContratoFranquiaResponseDTO(ContratoFranquia contratoFranquia) {
        this(contratoFranquia.getIdContratoFranquia(), new TiposEnumResponseDTO(contratoFranquia.getTipoPropiedadeIntelectual().getId(), contratoFranquia.getTipoPropiedadeIntelectual().getLabel()), new NitResponseDTO(contratoFranquia.getNit()),
                contratoFranquia.getTitulo(), contratoFranquia.getDescricao());
    }
}
