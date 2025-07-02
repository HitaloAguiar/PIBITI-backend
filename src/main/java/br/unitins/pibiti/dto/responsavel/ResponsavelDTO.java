package br.unitins.pibiti.dto.responsavel;

public record ResponsavelDTO(
    String nomeCompleto,
    String cpf,
    String email,
    String telefone,
    String cargo
) {
    
}
