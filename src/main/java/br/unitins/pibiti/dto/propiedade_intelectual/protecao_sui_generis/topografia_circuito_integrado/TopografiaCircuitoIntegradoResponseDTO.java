package br.unitins.pibiti.dto.propiedade_intelectual.protecao_sui_generis.topografia_circuito_integrado;

import br.unitins.pibiti.dto.nit.NitResponseDTO;
import br.unitins.pibiti.dto.propiedade_intelectual.TiposEnumResponseDTO;
import br.unitins.pibiti.model.TopografiaCircuitoIntegrado;

import java.util.List;

public record TopografiaCircuitoIntegradoResponseDTO(
        Long idTopografiaCircuitoIntegrado,
        TiposEnumResponseDTO tipoPropiedadeIntelectual,
        NitResponseDTO nit,
        String titulo,
        String descricao,
        List<String> autores
) {
    public TopografiaCircuitoIntegradoResponseDTO(TopografiaCircuitoIntegrado topografiaCircuitoIntegrado) {
        this(
                topografiaCircuitoIntegrado.getIdTopografiaCircuitoIntegrado(),
                new TiposEnumResponseDTO(
                        topografiaCircuitoIntegrado.getTipoPropiedadeIntelectual().getId(),
                        topografiaCircuitoIntegrado.getTipoPropiedadeIntelectual().getLabel()
                ),
                new NitResponseDTO(topografiaCircuitoIntegrado.getNit()),
                topografiaCircuitoIntegrado.getTitulo(),
                topografiaCircuitoIntegrado.getDescricao(),
                topografiaCircuitoIntegrado.getAutores()
        );
    }

}
