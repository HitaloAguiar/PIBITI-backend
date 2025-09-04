package br.unitins.pibiti.service.avaliacao_trl_pi;


import br.unitins.pibiti.dto.avaliacao_trl_pi.AvaliacaoTrlPiDTO;
import br.unitins.pibiti.dto.avaliacao_trl_pi.AvaliacaoTrlPiResponseDTO;

public interface AvaliacaoTrlPropriedadeIntelectualService {

    AvaliacaoTrlPiResponseDTO getAvaliacao(Long id);

    AvaliacaoTrlPiResponseDTO cadastrar(AvaliacaoTrlPiDTO avaliacaoDTO);

    void deletarAvaliacao(String cnpj, Long idAvaliacao);

}
