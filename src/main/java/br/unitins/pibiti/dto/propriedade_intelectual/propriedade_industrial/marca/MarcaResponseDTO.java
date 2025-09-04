package br.unitins.pibiti.dto.propriedade_intelectual.propriedade_industrial.marca;

import br.unitins.pibiti.dto.nit.NitResponseDTO;
import br.unitins.pibiti.dto.propriedade_intelectual.TiposEnumResponseDTO;
import br.unitins.pibiti.model.Marca;

import java.time.LocalDate;

public record MarcaResponseDTO(
        Long idMarca,
        TiposEnumResponseDTO tipoPropriedadeIntelectual,
        NitResponseDTO nit,
        String nome,
        String titular,
        LocalDate dataConcessao,
        String periodo,
        TiposEnumResponseDTO natureza,
        String classes,
        Boolean visualizacaoPublica
) {
    public MarcaResponseDTO(Marca marca) {
        this(marca.getIdMarca(), new TiposEnumResponseDTO(marca.getTipoPropriedadeIntelectual().getId(),
                        marca.getTipoPropriedadeIntelectual().getLabel()), new NitResponseDTO(marca.getNit()), marca.getNome(), marca.getTitular(),
                marca.getDataConcessao(), marca.getPeriodo(), new TiposEnumResponseDTO(marca.getNatureza().getId(), marca.getNatureza().getLabel()), marca.getClasses(), marca.getVisualizacaoPublica());
    }
}
