package br.unitins.pibiti.dto.propriedade_intelectual.direito_autoral.direito_autor;

import br.unitins.pibiti.dto.nit.NitResponseDTO;
import br.unitins.pibiti.dto.propriedade_intelectual.TiposEnumResponseDTO;
import br.unitins.pibiti.model.DireitoAutor;

import java.util.List;

public record DireitoAutorResponseDTO(
        Long idDireitoAutor,
        TiposEnumResponseDTO tipoPropriedadeIntelectual,
        NitResponseDTO nit,
        String titulo,
        List<String> autores,
        String descricao,
        List<TiposEnumResponseDTO> generos,
        Integer numeroTotalPaginasObra,
        Boolean adapatacaoOuTraducao,
        String tituloObraOriginal,
        List<String> autoresObraOriginal,
        Boolean visualizacaoPublica
) {
    public DireitoAutorResponseDTO(DireitoAutor direitoAutor) {
        this(
                direitoAutor.getIdDireitoAutor(),
                new TiposEnumResponseDTO(
                        direitoAutor.getTipoPropriedadeIntelectual().getId(),
                        direitoAutor.getTipoPropriedadeIntelectual().getLabel()
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
                direitoAutor.getAutoresObraOriginal(),
                direitoAutor.getVisualizacaoPublica()
        );
    }

}
