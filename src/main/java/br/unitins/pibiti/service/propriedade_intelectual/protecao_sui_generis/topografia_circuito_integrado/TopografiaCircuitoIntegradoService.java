package br.unitins.pibiti.service.propriedade_intelectual.protecao_sui_generis.topografia_circuito_integrado;


import java.util.List;

import br.unitins.pibiti.dto.propriedade_intelectual.protecao_sui_generis.topografia_circuito_integrado.TopografiaCircuitoIntegradoDTO;
import br.unitins.pibiti.dto.propriedade_intelectual.protecao_sui_generis.topografia_circuito_integrado.TopografiaCircuitoIntegradoResponseDTO;

public interface TopografiaCircuitoIntegradoService {

    TopografiaCircuitoIntegradoResponseDTO getTopografiaCircuitoIntegrado(Long id);

    TopografiaCircuitoIntegradoResponseDTO cadastrar(TopografiaCircuitoIntegradoDTO topografiaCircuitoIntegradoDTO);

    TopografiaCircuitoIntegradoResponseDTO atualizar(String cnpj, Long idTopografiaCircuitoIntegrado, TopografiaCircuitoIntegradoDTO topografiaCircuitoIntegradoDTO);

    void deletarTopografiaCircuitoIntegrado(String cnpj, Long idTopografiaCircuitoIntegrado);

    List<TopografiaCircuitoIntegradoResponseDTO> getAllByNit(Long idNit, int page, int pageSize, Boolean isAscending);

    List<TopografiaCircuitoIntegradoResponseDTO> getAllByNitFiltradoPorTitulo(Long idNit, String titulo, int page, int pageSize, Boolean isAscending);

    List<TopografiaCircuitoIntegradoResponseDTO> getAllPublico(int page, int pageSize, Boolean isAscending);

    List<TopografiaCircuitoIntegradoResponseDTO> getAllPublicoFiltradoPorTitulo(String titulo, int page, int pageSize, Boolean isAscending);
}
