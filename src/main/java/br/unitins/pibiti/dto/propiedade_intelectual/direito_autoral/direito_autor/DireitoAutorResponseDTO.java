package br.unitins.pibiti.dto.propiedade_intelectual.direito_autoral.direito_autor;

import br.unitins.pibiti.dto.nit.NitResponseDTO;
import br.unitins.pibiti.dto.propiedade_intelectual.TiposEnumResponseDTO;
import br.unitins.pibiti.model.DireitoAutor;

import java.util.List;

public record DireitoAutorResponseDTO(
        Long idDireitoAutor,
        TiposEnumResponseDTO tipoPropiedadeIntelectual,
        NitResponseDTO nit,
        String titulo,
        List<String> autores,
        String descricao,
        List<TiposEnumResponseDTO> generos,
        Integer numeroTotalPaginasObra,
        Boolean adapatacaoOuTraducao,
        String tituloObraOriginal,
        String autoresObraOriginal
) {
    public DireitoAutorResponseDTO(DireitoAutor direitoAutor) {
        this(
                direitoAutor.getIdDireitoAutor(),
                new TiposEnumResponseDTO(
                        direitoAutor.getTipoPropiedadeIntelectual().getId(),
                        direitoAutor.getTipoPropiedadeIntelectual().getLabel()
                ),
                new NitResponseDTO(direitoAutor.getNit()),
                direitoAutor.getTitulo(),
                direitoAutor.getAutores(),
                direitoAutor.getDescricao(),
                direitoAutor.getGeneros() != null
                        ? direitoAutor.getGeneros().stream()
                        .map(g -> new TiposEnumResponseDTO(g.getId(), g.getLabel()))
                        .toList()
                        : List.of(),
                direitoAutor.getNumeroTotalPaginasObra(),
                direitoAutor.getAdapatacaoOuTraducao(),
                direitoAutor.getTituloObraOriginal(),
                direitoAutor.getAutoresObraOriginal()
        );
    }

}
