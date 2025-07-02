package br.unitins.pibiti.dto.nit;

import java.time.LocalDate;

import br.unitins.pibiti.dto.responsavel.ResponsavelDTO;

public record NitDTO(
    String cnpj,
    String email,
    String telefone,
    LocalDate anoInicioAtividades,
    String ict,
    Boolean privacidade,
    String senha,
    ResponsavelDTO responsavelDTO
) {
    
}
