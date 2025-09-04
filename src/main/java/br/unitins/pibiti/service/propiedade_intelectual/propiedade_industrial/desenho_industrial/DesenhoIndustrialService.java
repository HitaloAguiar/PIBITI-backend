package br.unitins.pibiti.service.propiedade_intelectual.propiedade_industrial.desenho_industrial;


import java.util.List;

import br.unitins.pibiti.dto.propiedade_intelectual.propiedade_industrial.desenho_industrial.DesenhoIndustrialDTO;
import br.unitins.pibiti.dto.propiedade_intelectual.propiedade_industrial.desenho_industrial.DesenhoIndustrialResponseDTO;

public interface DesenhoIndustrialService {
    
    DesenhoIndustrialResponseDTO getDesenhoIndustrial(Long id);

    DesenhoIndustrialResponseDTO cadastrar(DesenhoIndustrialDTO desenhoIndustrialDTO);

    DesenhoIndustrialResponseDTO atualizar(String cnpj, Long idDesenhoIndustrial, DesenhoIndustrialDTO desenhoIndustrialDTO);

    void deletarDesenhoIndustrial(String cnpj, Long idDesenhoIndustrial);

    List<DesenhoIndustrialResponseDTO> getAllDesenhoIndustrial(Long idNit, int page, int pageSize);

    List<DesenhoIndustrialResponseDTO> getAllFiltradoPorTitulo(Long idNit, String titulo, int page, int pageSize);
}
