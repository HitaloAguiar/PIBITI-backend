package br.unitins.pibiti.service.propiedade_intelectual.protecao_sui_generis.topografia_circuito_integrado;


import br.unitins.pibiti.dto.propiedade_intelectual.protecao_sui_generis.topografia_circuito_integrado.TopografiaCircuitoIntegradoDTO;
import br.unitins.pibiti.dto.propiedade_intelectual.protecao_sui_generis.topografia_circuito_integrado.TopografiaCircuitoIntegradoResponseDTO;

public interface TopografiaCircuitoIntegradoService {

    TopografiaCircuitoIntegradoResponseDTO getTopografiaCircuitoIntegrado(Long id);

    TopografiaCircuitoIntegradoResponseDTO cadastrar(TopografiaCircuitoIntegradoDTO topografiaCircuitoIntegradoDTO);

    TopografiaCircuitoIntegradoResponseDTO atualizar(String cnpj, Long idTopografiaCircuitoIntegrado, TopografiaCircuitoIntegradoDTO topografiaCircuitoIntegradoDTO);

    void deletarTopografiaCircuitoIntegrado(String cnpj, Long idTopografiaCircuitoIntegrado);

}
