package br.unitins.pibiti.dto.nit;

import java.time.LocalDate;

import br.unitins.pibiti.dto.responsavel.ResponsavelDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record NitDTO(

    @NotBlank(message = "O campo CNPJ não pode estar nulo")
    @Size(min = 18, max = 18, message = "O campo CNPJ deve seguir o padrão xx.xxx.xxx/xxxx-xx, respeitando os sinais")
    String cnpj,

    @NotBlank(message = "O campo Email não pode estar nulo")
    String email,

    @NotBlank(message = "O campo Telefone não pode estar nulo")
    String telefone,

    @NotNull(message = "O campo Ano de Início de Atividades do NIT não pode estar nulo")
    LocalDate anoInicioAtividades,

    String ict,

    @NotNull(message = "Marque uma das opções")
    Boolean privacidade,

    @NotNull
    ResponsavelDTO responsavelDTO,

    @NotNull
    SenhaDTO senhaDTO
) {
    
}
