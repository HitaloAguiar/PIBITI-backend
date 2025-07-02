package br.unitins.pibiti.service.nit;

import br.unitins.pibiti.dto.nit.NitDTO;
import br.unitins.pibiti.dto.nit.NitResponseDTO;

public interface NitService {
    
    NitResponseDTO getNit(Long id);

    NitResponseDTO cadastrar(NitDTO nitDTO);

    NitResponseDTO atualizar(Long id, NitDTO nitDTO);
}
