package br.unitins.pibiti.dto.propiedade_intelectual.protecao_sui_generis.topografia_circuito_integrado;

import java.util.List;

public record TopografiaCircuitoIntegradoDTO(
        Long idNit,
        String titulo,
        String descricao,
        List<String> autores
) {
}
