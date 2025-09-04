package br.unitins.pibiti.dto.propriedade_intelectual.direito_autoral.registro_programa_computador;

import java.util.List;

public record RegistroProgramaComputadorDTO(
        Long idNit,
        String titulo,
        String descricao,
        List<String> autores,
        List<String> linguagens,
        String campoAplicacao,
        String tipoPrograma,
        Boolean visualizacaoPublica
) {
}
