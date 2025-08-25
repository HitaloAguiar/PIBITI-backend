package br.unitins.pibiti.dto.avaliacao_maturidade;

import java.time.LocalDate;

import br.unitins.pibiti.model.AvaliacaoMaturidade;

public record AvaliacaoMaturidadeGraficoResponseDTO(
    Long id,
    Double img,
    LocalDate dataAvaliacao
) {
    
    public AvaliacaoMaturidadeGraficoResponseDTO (AvaliacaoMaturidade avaliacaoMaturidade) {
        this(avaliacaoMaturidade.getIdAvaliacaoMaturidade(), avaliacaoMaturidade.getImg(), avaliacaoMaturidade.getCreatedAt().toLocalDate());
    }
}
