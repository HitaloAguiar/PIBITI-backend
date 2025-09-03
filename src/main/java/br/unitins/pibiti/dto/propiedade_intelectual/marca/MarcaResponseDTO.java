package br.unitins.pibiti.dto.propiedade_intelectual.marca;

import br.unitins.pibiti.dto.nit.NitResponseDTO;
import br.unitins.pibiti.model.Marca;

import java.time.LocalDate;

public record MarcaResponseDTO(
        Long idMarca,
        TipoPropiedadeIntelectualResponse tipoPropiedadeIntelectual,
        NitResponseDTO nit,
        String nome,
        String titular,
        LocalDate dataConcessao,
        String periodo,
        String natureza,
        String classes
) {
    public MarcaResponseDTO(Marca marca) {
        this(marca.getIdMarca(), new TipoPropiedadeIntelectualResponse(marca.getTipoPropiedadeIntelectual().getId(), marca.getTipoPropiedadeIntelectual().getLabel()), new NitResponseDTO(marca.getNit()), marca.getNome(), marca.getTitular(), marca.getDataConcessao(), marca.getPeriodo(), marca.getNatureza().getLabel(), marca.getClasses());
    }
}
