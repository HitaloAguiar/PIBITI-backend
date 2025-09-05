package br.unitins.pibiti.service.propriedade_intelectual.propriedade_industrial.contrato_franquia;


import java.util.List;

import br.unitins.pibiti.dto.propriedade_intelectual.propriedade_industrial.contato_franquia.ContratoFranquiaDTO;
import br.unitins.pibiti.dto.propriedade_intelectual.propriedade_industrial.contato_franquia.ContratoFranquiaResponseDTO;

public interface ContratoFranquiaService {

    ContratoFranquiaResponseDTO getContratoFranquia(Long id);

    ContratoFranquiaResponseDTO cadastrar(ContratoFranquiaDTO contratoDTO);

    ContratoFranquiaResponseDTO atualizar(String cnpj, Long idContratoFranquia, ContratoFranquiaDTO contratoDTO);

    void deletarContratoFranquia(String cnpj, Long idContratoFranquia);

    List<ContratoFranquiaResponseDTO> getAllByNit(Long idNit, int page, int pageSize, Boolean isAscending);

    List<ContratoFranquiaResponseDTO> getAllByNitFiltradoPorTitulo(Long idNit, String titulo, int page, int pageSize, Boolean isAscending);

    List<ContratoFranquiaResponseDTO> getAllPublico(int page, int pageSize, Boolean isAscending);

    List<ContratoFranquiaResponseDTO> getAllPublicoFiltradoPorTitulo(String titulo, int page, int pageSize, Boolean isAscending);
}
