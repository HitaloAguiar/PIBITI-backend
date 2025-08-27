package br.unitins.pibiti.dto.nit;

import java.util.List;

public record ServicosFornecidoDTO(
    Long idNit,
    List<Long> servicosFornecidos
) {
    
}
