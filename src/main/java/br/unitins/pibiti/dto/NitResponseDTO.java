package br.unitins.pibiti.dto;

import java.time.LocalDate;
import java.util.List;

import br.unitins.pibiti.model.Nit;

public record NitResponseDTO(
    Long idNit,
    String cnpj,
    String email,
    String telefone,
    LocalDate anoInicioAtividades,
    String ict,
    Boolean privacidade,
    String senha,
    String fotoPerfil,
    ResponsavelResponseDTO responsavel,
    List<String> servicos
) {
    
    public NitResponseDTO (Nit nit) {

        this(nit.getIdNit(), nit.getCnpj(), nit.getEmail(), nit.getTelefone(), nit.getAnoInicioAtividades(), nit.getIct(), nit.getPrivacidade(), nit.getSenha(), nit.getFotoPerfil(), new ResponsavelResponseDTO(nit.getResponsavel()), nit.getServicos().stream().map(servico -> servico.getServico().getNome()).toList());
    }
}
