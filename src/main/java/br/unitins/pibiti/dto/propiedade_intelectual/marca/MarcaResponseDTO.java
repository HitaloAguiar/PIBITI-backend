package br.unitins.pibiti.dto.propiedade_intelectual.marca;

import br.unitins.pibiti.dto.nit.NitResponseDTO;
import br.unitins.pibiti.dto.propiedade_intelectual.TiposEnumResponseDTO;
import br.unitins.pibiti.model.Marca;

import java.time.LocalDate;

public record MarcaResponseDTO(
        Long idMarca,
        TiposEnumResponseDTO tipoPropiedadeIntelectual,
        NitResponseDTO nit,
        String nome,
        String titular,
        LocalDate dataConcessao,
        String periodo,
        TiposEnumResponseDTO natureza,
        String classes
) {
    public MarcaResponseDTO(Marca marca) {
        this(marca.getIdMarca(), new TiposEnumResponseDTO(marca.getTipoPropiedadeIntelectual().getId(), marca.getTipoPropiedadeIntelectual().getLabel()), new NitResponseDTO(marca.getNit()), marca.getNome(), marca.getTitular(), marca.getDataConcessao(), marca.getPeriodo(), new TiposEnumResponseDTO(marca.getNatureza().getId(), marca.getNatureza().getLabel()), marca.getClasses());
    }
}
