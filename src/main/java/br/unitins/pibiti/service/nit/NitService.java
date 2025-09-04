package br.unitins.pibiti.service.nit;

import br.unitins.pibiti.dto.nit.NitDTO;
import br.unitins.pibiti.dto.nit.NitResponseDTO;
import br.unitins.pibiti.dto.nit.NitUpdateDTO;
import br.unitins.pibiti.dto.nit.SenhaDTO;
import br.unitins.pibiti.dto.nit.ServicosFornecidoDTO;
import br.unitins.pibiti.model.Nit;

public interface NitService {
    
    NitResponseDTO getNit(Long id);

    NitResponseDTO cadastrar(NitDTO nitDTO);

    NitResponseDTO atualizar(String cnpj, NitUpdateDTO nitDTO);

    void cadastrarServicosFornecidos(ServicosFornecidoDTO servicosFornecidoDTO);

    Nit getByLoginAndSenha(String cnpjOuEmail, String senha);

    NitResponseDTO getNitLogado(String login);

    void enviarEmailRedefinirSenha(String email);

    void redefinirSenha(String cnpj, SenhaDTO senhaDTO);
}
