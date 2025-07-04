package br.unitins.pibiti.dto.responsavel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ResponsavelDTO(

    @NotBlank(message = "O campo Nome Completo não pode estar nulo")
    String nomeCompleto,

    @NotBlank(message = "O campo CPF não pode estar nulo")
    @Size(min = 14, max = 14, message = "O campo CPF deve seguir o padrão xxx.xxx.xxx-xx, respeitando os sinais")
    String cpf,

    @NotBlank(message = "O campo Email não pode estar nulo")
    String email,

    @NotBlank(message = "O campo Telefone não pode estar nulo")
    String telefone,

    @NotBlank(message = "O campo Cargo não pode estar nulo")
    String cargo
) {
    
}
