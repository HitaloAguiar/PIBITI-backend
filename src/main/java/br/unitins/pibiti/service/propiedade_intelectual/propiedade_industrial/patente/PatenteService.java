package br.unitins.pibiti.service.propiedade_intelectual.propiedade_industrial.patente;


import br.unitins.pibiti.dto.propiedade_intelectual.propiedade_industrial.patente.PatenteDTO;
import br.unitins.pibiti.dto.propiedade_intelectual.propiedade_industrial.patente.PatenteResponseDTO;

public interface PatenteService {
    
    PatenteResponseDTO getPatente(Long id);

    PatenteResponseDTO cadastrar(PatenteDTO patenteDTO);

    PatenteResponseDTO atualizar(String cnpj, Long idPatente, PatenteDTO patenteDTO);

    void deletarPatente(String cnpj, Long idPatente);

}
