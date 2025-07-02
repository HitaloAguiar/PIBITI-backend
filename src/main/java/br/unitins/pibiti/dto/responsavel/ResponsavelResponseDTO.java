package br.unitins.pibiti.dto.responsavel;

import br.unitins.pibiti.model.Responsavel;

public record ResponsavelResponseDTO(
    Long idResponsavel,
    String nomeCompleto,
    String cpf,
    String email,
    String telefone,
    String cargo
) {
    
    public ResponsavelResponseDTO (Responsavel responsavel) {

        this(responsavel.getIdResponsavel(), responsavel.getNomeCompleto(), responsavel.getCpf(), responsavel.getEmail(), responsavel.getTelefone(), responsavel.getCargo());
    }
}
