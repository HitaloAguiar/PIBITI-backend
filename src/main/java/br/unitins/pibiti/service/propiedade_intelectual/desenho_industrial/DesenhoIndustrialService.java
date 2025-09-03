package br.unitins.pibiti.service.propiedade_intelectual.desenho_industrial;


import br.unitins.pibiti.dto.propiedade_intelectual.desenho_industrial.DesenhoIndustrialDTO;
import br.unitins.pibiti.dto.propiedade_intelectual.desenho_industrial.DesenhoIndustrialResponseDTO;

public interface DesenhoIndustrialService {
    
    DesenhoIndustrialResponseDTO getDesenhoIndustrial(Long id);

    DesenhoIndustrialResponseDTO cadastrar(DesenhoIndustrialDTO desenhoIndustrialDTO);

    DesenhoIndustrialResponseDTO atualizar(String cnpj, Long idDesenhoIndustrial, DesenhoIndustrialDTO desenhoIndustrialDTO);

    void deletarDesenhoIndustrial(String cnpj, Long idDesenhoIndustrial);

}
