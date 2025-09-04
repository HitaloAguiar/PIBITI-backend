package br.unitins.pibiti.service.propriedade_intelectual.protecao_sui_generis.cultivar;


import java.util.List;

import br.unitins.pibiti.dto.propriedade_intelectual.protecao_sui_generis.cultivar.CultivarDTO;
import br.unitins.pibiti.dto.propriedade_intelectual.protecao_sui_generis.cultivar.CultivarResponseDTO;

public interface CultivarService {

    CultivarResponseDTO getCultivar(Long id);

    CultivarResponseDTO cadastrar(CultivarDTO cultivarDTO);

    CultivarResponseDTO atualizar(String cnpj, Long idCultivar, CultivarDTO cultivarDTO);

    void deletarCultivar(String cnpj, Long idCultivar);

    List<CultivarResponseDTO> getAllCultivar(Long idNit, int page, int pageSize);

    List<CultivarResponseDTO> getAllFiltradoPorTitulo(Long idNit, String titulo, int page, int pageSize);
}
