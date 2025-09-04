package br.unitins.pibiti.dto.propriedade_intelectual.protecao_sui_generis.cultivar;

import br.unitins.pibiti.dto.nit.NitResponseDTO;
import br.unitins.pibiti.dto.propriedade_intelectual.TiposEnumResponseDTO;
import br.unitins.pibiti.model.Cultivar;

public record CultivarResponseDTO(
        Long idCultivar,
        TiposEnumResponseDTO tipoPropriedadeIntelectual,
        NitResponseDTO nit,
        String titulo,
        String descricao,
        TiposEnumResponseDTO categoria,
        Boolean visualizacaoPublica
) {
    public CultivarResponseDTO(Cultivar cultivar) {
        this(
                cultivar.getIdCultivar(),
                new TiposEnumResponseDTO(
                        cultivar.getTipoPropriedadeIntelectual().getId(),
                        cultivar.getTipoPropriedadeIntelectual().getLabel()
                ),
                new NitResponseDTO(cultivar.getNit()),
                cultivar.getTitulo(),
                cultivar.getDescricao(),
                new TiposEnumResponseDTO(
                        cultivar.getCategoria().getId(),
                        cultivar.getCategoria().getLabel()
                ),
                cultivar.getVisualizacaoPublica()
        );
    }

}
