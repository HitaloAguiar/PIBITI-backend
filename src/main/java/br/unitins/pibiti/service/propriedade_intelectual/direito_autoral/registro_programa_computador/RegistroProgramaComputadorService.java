package br.unitins.pibiti.service.propriedade_intelectual.direito_autoral.registro_programa_computador;


import java.util.List;

import br.unitins.pibiti.dto.propriedade_intelectual.direito_autoral.registro_programa_computador.RegistroProgramaComputadorDTO;
import br.unitins.pibiti.dto.propriedade_intelectual.direito_autoral.registro_programa_computador.RegistroProgramaComputadorResponseDTO;

public interface RegistroProgramaComputadorService {

    RegistroProgramaComputadorResponseDTO getRegistroProgramaComputador(Long id);

    RegistroProgramaComputadorResponseDTO cadastrar(RegistroProgramaComputadorDTO registroProgramaDTO);

    RegistroProgramaComputadorResponseDTO atualizar(String cnpj, Long idRegistroProgramaComputador, RegistroProgramaComputadorDTO registroProgramaDTO);

    void deletarRegistroProgramaComputador(String cnpj, Long idRegistroProgramaComputador);

    List<RegistroProgramaComputadorResponseDTO> getAllRegistroProgramaComputador(Long idNit, int page, int pageSize);

    List<RegistroProgramaComputadorResponseDTO> getAllFiltradoPorTitulo(Long idNit, String titulo, int page, int pageSize);
}
