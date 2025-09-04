package br.unitins.pibiti.dto.propriedade_intelectual.propriedade_industrial.contato_franquia;

import br.unitins.pibiti.dto.nit.NitResponseDTO;
import br.unitins.pibiti.dto.propriedade_intelectual.TiposEnumResponseDTO;
import br.unitins.pibiti.model.ContratoFranquia;

public record ContratoFranquiaResponseDTO(
        Long idContratoFranquia,
        TiposEnumResponseDTO tipoPropriedadeIntelectual,
        NitResponseDTO nit,
        String titulo,
        String descricao,
        Boolean visualizacaoPublica
) {
    public ContratoFranquiaResponseDTO(ContratoFranquia contratoFranquia) {
        this(contratoFranquia.getIdContratoFranquia(), new TiposEnumResponseDTO(contratoFranquia.getTipoPropriedadeIntelectual().getId(), contratoFranquia.getTipoPropriedadeIntelectual().getLabel()), new NitResponseDTO(contratoFranquia.getNit()),
                contratoFranquia.getTitulo(), contratoFranquia.getDescricao(), contratoFranquia.getVisualizacaoPublica());
    }
}
