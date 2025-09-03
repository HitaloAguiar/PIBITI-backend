package br.unitins.pibiti.service.propiedade_intelectual.direito_autoral.registro_programa_computador;


import br.unitins.pibiti.dto.propiedade_intelectual.direito_autoral.registro_programa_computador.RegistroProgramaComputadorDTO;
import br.unitins.pibiti.dto.propiedade_intelectual.direito_autoral.registro_programa_computador.RegistroProgramaComputadorResponseDTO;

public interface RegistroProgramaComputadorService {

    RegistroProgramaComputadorResponseDTO getRegistroProgramaComputador(Long id);

    RegistroProgramaComputadorResponseDTO cadastrar(RegistroProgramaComputadorDTO registroProgramaDTO);

    RegistroProgramaComputadorResponseDTO atualizar(String cnpj, Long idRegistroProgramaComputador, RegistroProgramaComputadorDTO registroProgramaDTO);

    void deletarRegistroProgramaComputador(String cnpj, Long idRegistroProgramaComputador);

}
