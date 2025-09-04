package br.unitins.pibiti.service.propiedade_intelectual.propiedade_industrial.contrato_franquia;


import java.util.List;

import br.unitins.pibiti.dto.propiedade_intelectual.propiedade_industrial.contato_franquia.ContratoFranquiaDTO;
import br.unitins.pibiti.dto.propiedade_intelectual.propiedade_industrial.contato_franquia.ContratoFranquiaResponseDTO;

public interface ContratoFranquiaService {

    ContratoFranquiaResponseDTO getContratoFranquia(Long id);

    ContratoFranquiaResponseDTO cadastrar(ContratoFranquiaDTO contratoDTO);

    ContratoFranquiaResponseDTO atualizar(String cnpj, Long idContratoFranquia, ContratoFranquiaDTO contratoDTO);

    void deletarContratoFranquia(String cnpj, Long idContratoFranquia);

    List<ContratoFranquiaResponseDTO> getAllContratoFranquia(Long idNit, int page, int pageSize);

    List<ContratoFranquiaResponseDTO> getAllFiltradoPorTitulo(Long idNit, String titulo, int page, int pageSize);
}
