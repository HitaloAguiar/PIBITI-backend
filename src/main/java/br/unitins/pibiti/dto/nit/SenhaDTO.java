package br.unitins.pibiti.dto.nit;

public record SenhaDTO(
    String senha,
    String confirmarSenha
) {
    
} // TODO::confirmar como vai ser o cadastro de senha, se vai ser junto com o DTO de nit ou DTO próprio
