package br.unitins.pibiti.service.propiedade_intelectual.contrato_franquia;


import br.unitins.pibiti.dto.propiedade_intelectual.contato_franquia.ContratoFranquiaDTO;
import br.unitins.pibiti.dto.propiedade_intelectual.contato_franquia.ContratoFranquiaResponseDTO;

public interface ContratoFranquiaService {

    ContratoFranquiaResponseDTO getContratoFranquia(Long id);

    ContratoFranquiaResponseDTO cadastrar(ContratoFranquiaDTO contratoDTO);

    ContratoFranquiaResponseDTO atualizar(String cnpj, Long idContratoFranquia, ContratoFranquiaDTO contratoDTO);

    void deletarContratoFranquia(String cnpj, Long idContratoFranquia);

}
