package br.unitins.pibiti.dto.avaliacao_maturidade;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.unitins.pibiti.dto.nit.NitResponseDTO;
import br.unitins.pibiti.model.AvaliacaoMaturidade;
import br.unitins.pibiti.model.DimensaoAvaliacao;

public record AvaliacaoMaturidadeResponseDTO(
        Long id,
        Double img,
        String nivelMaturidade,
        Map<String, Double> imds,
        List<VariavelAvaliacaoResponseDTO> variaveisSelecionadas,
        NitResponseDTO nit,
        LocalDate dataAvaliacao
) {

    public AvaliacaoMaturidadeResponseDTO (AvaliacaoMaturidade avaliacaoMaturidade, List<DimensaoAvaliacao> listDimensaoAvaliacao, List<VariavelAvaliacaoResponseDTO> variaveisSelecionadas) {
        this(avaliacaoMaturidade.getIdAvaliacaoMaturidade(), avaliacaoMaturidade.getImg(), avaliacaoMaturidade.getNivelMaturidade(), gerarMapImds(listDimensaoAvaliacao), variaveisSelecionadas, new NitResponseDTO(avaliacaoMaturidade.getNit()), avaliacaoMaturidade.getCreatedAt().toLocalDate());
    }

    private static Map<String, Double> gerarMapImds(List<DimensaoAvaliacao> listDimensaoAvaliacao) {

        Map<String, Double> map = new HashMap<>();

        for (DimensaoAvaliacao dimensaoAvaliacao : listDimensaoAvaliacao) {

            map.put(dimensaoAvaliacao.getDimensao().getNome(), dimensaoAvaliacao.getImd());
        }

        return map;
    }
}