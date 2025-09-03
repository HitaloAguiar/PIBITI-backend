package br.unitins.pibiti.dto.propiedade_intelectual.desenho_industrial;

import java.time.LocalDate;

public record DesenhoIndustrialDTO(
        Long idNit,
        String titulo,
        String descricao,
        LocalDate dataConcessao,
        String periodo,
        Long idTipoDesenhoIndustrial
) {
}
