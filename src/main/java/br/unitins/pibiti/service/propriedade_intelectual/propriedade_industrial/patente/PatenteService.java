package br.unitins.pibiti.service.propriedade_intelectual.propriedade_industrial.patente;


import java.util.List;

import br.unitins.pibiti.dto.propriedade_intelectual.propriedade_industrial.patente.PatenteDTO;
import br.unitins.pibiti.dto.propriedade_intelectual.propriedade_industrial.patente.PatenteResponseDTO;

public interface PatenteService {
    
    PatenteResponseDTO getPatente(Long id);

    PatenteResponseDTO cadastrar(PatenteDTO patenteDTO);

    PatenteResponseDTO atualizar(String cnpj, Long idPatente, PatenteDTO patenteDTO);

    void deletarPatente(String cnpj, Long idPatente);

    List<PatenteResponseDTO> getAllByNit(Long idNit, int page, int pageSize, Boolean isAscending);

    List<PatenteResponseDTO> getAllByNitFiltradoPorTitulo(Long idNit, String titulo, int page, int pageSize, Boolean isAscending);

    List<PatenteResponseDTO> getAllPublico(int page, int pageSize, Boolean isAscending);

    List<PatenteResponseDTO> getAllPublicoFiltradoPorTitulo(String titulo, int page, int pageSize, Boolean isAscending);
}
