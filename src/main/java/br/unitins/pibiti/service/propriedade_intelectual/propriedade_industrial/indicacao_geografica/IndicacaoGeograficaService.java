package br.unitins.pibiti.service.propriedade_intelectual.propriedade_industrial.indicacao_geografica;


import java.util.List;

import br.unitins.pibiti.dto.propriedade_intelectual.propriedade_industrial.indicacao_geografica.IndicacaoGeograficaDTO;
import br.unitins.pibiti.dto.propriedade_intelectual.propriedade_industrial.indicacao_geografica.IndicacaoGeograficaResponseDTO;

public interface IndicacaoGeograficaService {
    
    IndicacaoGeograficaResponseDTO getIndicacaoGeografica(Long id);

    IndicacaoGeograficaResponseDTO cadastrar(IndicacaoGeograficaDTO indicacaoGeograficaDTO);

    IndicacaoGeograficaResponseDTO atualizar(String cnpj, Long idIndicacaoGeografica, IndicacaoGeograficaDTO indicacaoGeograficaDTO);

    void deletarIndicacaoGeografica(String cnpj, Long idIndicacaoGeografica);

    List<IndicacaoGeograficaResponseDTO> getAllIndicacaoGeografica(Long idNit, int page, int pageSize, Boolean isAscending);

    List<IndicacaoGeograficaResponseDTO> getAllFiltradoPorTitulo(Long idNit, String titulo, int page, int pageSize, Boolean isAscending);
}
