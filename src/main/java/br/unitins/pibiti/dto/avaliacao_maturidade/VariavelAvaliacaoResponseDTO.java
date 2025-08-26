package br.unitins.pibiti.dto.avaliacao_maturidade;

import br.unitins.pibiti.model.Variavel;
import br.unitins.pibiti.model.VariavelAvaliacao;

public record VariavelAvaliacaoResponseDTO(
    Long idVariavel,
    String nome,
    Boolean selecionado,
    String nomeDimensao
) {
    
    public VariavelAvaliacaoResponseDTO (Variavel variavel, Integer selecionado) {

        this(variavel.getIdVariavelMaturidadeNIT(), variavel.getNome(), selecionado == 0 ? false : true, variavel.getDimensao().getNome());
    }

    public VariavelAvaliacaoResponseDTO (VariavelAvaliacao variavelAvaliacao) {

        this(variavelAvaliacao.getVariavel().getIdVariavelMaturidadeNIT(), variavelAvaliacao.getVariavel().getNome(), variavelAvaliacao.getSelecionado(), variavelAvaliacao.getVariavel().getDimensao().getNome());
    }
}
