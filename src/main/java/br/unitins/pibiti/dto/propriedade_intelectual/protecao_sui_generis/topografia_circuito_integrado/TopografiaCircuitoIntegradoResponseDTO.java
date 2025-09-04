package br.unitins.pibiti.dto.propriedade_intelectual.protecao_sui_generis.topografia_circuito_integrado;

import br.unitins.pibiti.dto.nit.NitResponseDTO;
import br.unitins.pibiti.dto.propriedade_intelectual.TiposEnumResponseDTO;
import br.unitins.pibiti.model.TopografiaCircuitoIntegrado;

import java.util.List;

public record TopografiaCircuitoIntegradoResponseDTO(
        Long idTopografiaCircuitosIntegrados,
        TiposEnumResponseDTO tipoPropriedadeIntelectual,
        NitResponseDTO nit,
        String titulo,
        String descricao,
        List<String> autores,
        Boolean visualizacaoPublica
) {
    public TopografiaCircuitoIntegradoResponseDTO(TopografiaCircuitoIntegrado topografiaCircuitoIntegrado) {
        this(
                topografiaCircuitoIntegrado.getIdTopografiaCircuitoIntegrado(),
                new TiposEnumResponseDTO(
                        topografiaCircuitoIntegrado.getTipoPropriedadeIntelectual().getId(),
                        topografiaCircuitoIntegrado.getTipoPropriedadeIntelectual().getLabel()
                ),
                new NitResponseDTO(topografiaCircuitoIntegrado.getNit()),
                topografiaCircuitoIntegrado.getTitulo(),
                topografiaCircuitoIntegrado.getDescricao(),
                topografiaCircuitoIntegrado.getAutores(),
                topografiaCircuitoIntegrado.getVisualizacaoPublica()
        );
    }

}
