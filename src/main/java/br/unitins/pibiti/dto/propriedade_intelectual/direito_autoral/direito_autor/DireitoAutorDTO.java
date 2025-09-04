package br.unitins.pibiti.dto.propriedade_intelectual.direito_autoral.direito_autor;

import java.util.List;

public record DireitoAutorDTO(
        Long idNit,
        String titulo,
        List<String> autores,
        String descricao,
        List<Long> idsGenero,
        Integer numeroTotalPaginasObra,
        Boolean adapatacaoOuTraducao,
        String tituloObraOriginal,
        List<String> autoresObraOriginal,
        Boolean visualizacaoPublica
) {
}
