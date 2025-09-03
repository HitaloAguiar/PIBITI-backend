package br.unitins.pibiti.service.propiedade_intelectual.protecao_sui_generis.cultivar;


import br.unitins.pibiti.dto.propiedade_intelectual.protecao_sui_generis.cultivar.CultivarDTO;
import br.unitins.pibiti.dto.propiedade_intelectual.protecao_sui_generis.cultivar.CultivarResponseDTO;

public interface CultivarService {

    CultivarResponseDTO getCultivar(Long id);

    CultivarResponseDTO cadastrar(CultivarDTO cultivarDTO);

    CultivarResponseDTO atualizar(String cnpj, Long idCultivar, CultivarDTO cultivarDTO);

    void deletarCultivar(String cnpj, Long idCultivar);

}
