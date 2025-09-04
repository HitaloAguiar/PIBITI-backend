package br.unitins.pibiti.service.propiedade_intelectual.propiedade_industrial.indicacao_geografica;


import java.util.List;

import br.unitins.pibiti.dto.propiedade_intelectual.propiedade_industrial.indicacao_geografica.IndicacaoGeograficaDTO;
import br.unitins.pibiti.dto.propiedade_intelectual.propiedade_industrial.indicacao_geografica.IndicacaoGeograficaResponseDTO;

public interface IndicacaoGeograficaService {
    
    IndicacaoGeograficaResponseDTO getIndicacaoGeografica(Long id);

    IndicacaoGeograficaResponseDTO cadastrar(IndicacaoGeograficaDTO indicacaoGeograficaDTO);

    IndicacaoGeograficaResponseDTO atualizar(String cnpj, Long idIndicacaoGeografica, IndicacaoGeograficaDTO indicacaoGeograficaDTO);

    void deletarIndicacaoGeografica(String cnpj, Long idIndicacaoGeografica);

    List<IndicacaoGeograficaResponseDTO> getAllIndicacaoGeografica(Long idNit, int page, int pageSize);

    List<IndicacaoGeograficaResponseDTO> getAllFiltradoPorTitulo(Long idNit, String titulo, int page, int pageSize);
}
