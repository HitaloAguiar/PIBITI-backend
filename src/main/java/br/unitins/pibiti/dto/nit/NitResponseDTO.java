package br.unitins.pibiti.dto.nit;

import java.util.List;

import br.unitins.pibiti.dto.responsavel.ResponsavelResponseDTO;
import br.unitins.pibiti.model.Nit;

public record NitResponseDTO(
    Long idNit,
    String cnpj,
    String email,
    String telefone,
    Integer anoInicioAtividades,
    String ict,
    Boolean privacidade,
    String fotoPerfil,
    ResponsavelResponseDTO responsavel,
    List<String> servicos
) {
    
    public NitResponseDTO (Nit nit) {
        this(nit.getIdNit(), nit.getCnpj(), nit.getEmail(), nit.getTelefone(), nit.getAnoInicioAtividades(), nit.getIct(), nit.getPrivacidade(), nit.getFotoPerfil(), new ResponsavelResponseDTO(nit.getResponsavel()), nit.getServicos() == null? null : nit.getServicos().stream().map(servico -> servico.getServico().getNome()).toList());
    }
}
