package br.unitins.pibiti.service.avaliacao_trl_pi;


import br.unitins.pibiti.dto.avaliacao_trl_pi.AvaliacaoTrlPiDTO;
import br.unitins.pibiti.dto.avaliacao_trl_pi.AvaliacaoTrlPiResponseDTO;

public interface AvaliacaoTrlPropiedadeIntelectualService {

    AvaliacaoTrlPiResponseDTO getAvaliacao(Long id);

    AvaliacaoTrlPiResponseDTO cadastrar(String cnpj, AvaliacaoTrlPiDTO avaliacaoDTO);

    void deletarAvaliacao(String cnpj, Long idAvaliacao);

}
