package br.unitins.pibiti.service.propiedade_intelectual.indicacao_geografica;


import br.unitins.pibiti.dto.propiedade_intelectual.indicacao_geografica.IndicacaoGeograficaDTO;
import br.unitins.pibiti.dto.propiedade_intelectual.indicacao_geografica.IndicacaoGeograficaResponseDTO;

public interface IndicacaoGeograficaService {
    
    IndicacaoGeograficaResponseDTO getIndicacaoGeografica(Long id);

    IndicacaoGeograficaResponseDTO cadastrar(IndicacaoGeograficaDTO indicacaoGeograficaDTO);

    IndicacaoGeograficaResponseDTO atualizar(String cnpj, Long idIndicacaoGeografica, IndicacaoGeograficaDTO indicacaoGeograficaDTO);

    void deletarIndicacaoGeografica(String cnpj, Long idIndicacaoGeografica);

}
