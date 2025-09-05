package br.unitins.pibiti.service.propriedade_intelectual.propriedade_industrial.desenho_industrial;


import java.util.List;

import br.unitins.pibiti.dto.propriedade_intelectual.propriedade_industrial.desenho_industrial.DesenhoIndustrialDTO;
import br.unitins.pibiti.dto.propriedade_intelectual.propriedade_industrial.desenho_industrial.DesenhoIndustrialResponseDTO;

public interface DesenhoIndustrialService {
    
    DesenhoIndustrialResponseDTO getDesenhoIndustrial(Long id);

    DesenhoIndustrialResponseDTO cadastrar(DesenhoIndustrialDTO desenhoIndustrialDTO);

    DesenhoIndustrialResponseDTO atualizar(String cnpj, Long idDesenhoIndustrial, DesenhoIndustrialDTO desenhoIndustrialDTO);

    void deletarDesenhoIndustrial(String cnpj, Long idDesenhoIndustrial);

    List<DesenhoIndustrialResponseDTO> getAllDesenhoIndustrial(Long idNit, int page, int pageSize, Boolean isAscending);

    List<DesenhoIndustrialResponseDTO> getAllFiltradoPorTitulo(Long idNit, String titulo, int page, int pageSize, Boolean isAscending);
}
