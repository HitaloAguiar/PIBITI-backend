package br.unitins.pibiti.dto.propiedade_intelectual.direito_autoral.registro_programa_computador;

import br.unitins.pibiti.dto.nit.NitResponseDTO;
import br.unitins.pibiti.dto.propiedade_intelectual.TiposEnumResponseDTO;
import br.unitins.pibiti.model.RegistroProgramaComputador;

import java.util.List;

public record RegistroProgramaComputadorResponseDTO(
        Long idRegistroComputador,
        TiposEnumResponseDTO tipoPropiedadeIntelectual,
        NitResponseDTO nit,
        String titulo,
        String descricao,
        List<String> autores,
        List<String> linguagens,
        String campoAplicacao,
        String tipoPrograma,
        Boolean visualizacaoPublica
) {
    public RegistroProgramaComputadorResponseDTO(RegistroProgramaComputador registroProgramaComputador) {
        this(
                registroProgramaComputador.getIdRegistroProgramaComputador(),
                new TiposEnumResponseDTO(
                        registroProgramaComputador.getTipoPropiedadeIntelectual().getId(),
                        registroProgramaComputador.getTipoPropiedadeIntelectual().getLabel()
                ),
                new NitResponseDTO(registroProgramaComputador.getNit()),
                registroProgramaComputador.getTitulo(),
                registroProgramaComputador.getDescricao(),
                registroProgramaComputador.getAutores(),
                registroProgramaComputador.getLinguagens(),
                registroProgramaComputador.getCampoAplicacao(),
                registroProgramaComputador.getTipoPrograma(),
                registroProgramaComputador.getVisualizacaoPublica()
        );
    }

}
