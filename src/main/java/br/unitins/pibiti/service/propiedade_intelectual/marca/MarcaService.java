package br.unitins.pibiti.service.propiedade_intelectual.marca;


import br.unitins.pibiti.dto.propiedade_intelectual.marca.MarcaDTO;
import br.unitins.pibiti.dto.propiedade_intelectual.marca.MarcaResponseDTO;

public interface MarcaService {
    
    MarcaResponseDTO getMarca(Long id);

    MarcaResponseDTO cadastrar(MarcaDTO marcaDTO);

    MarcaResponseDTO atualizar(String cnpj, Long idMarca, MarcaDTO marcaDTO);

    void deletarMarca(String cnpj, Long idMarca);

}
