package br.unitins.pibiti.service.propriedade_intelectual.propriedade_industrial.marca;


import java.util.List;

import br.unitins.pibiti.dto.propriedade_intelectual.propriedade_industrial.marca.MarcaDTO;
import br.unitins.pibiti.dto.propriedade_intelectual.propriedade_industrial.marca.MarcaResponseDTO;

public interface MarcaService {
    
    MarcaResponseDTO getMarca(Long id);

    MarcaResponseDTO cadastrar(MarcaDTO marcaDTO);

    MarcaResponseDTO atualizar(String cnpj, Long idMarca, MarcaDTO marcaDTO);

    void deletarMarca(String cnpj, Long idMarca);

    List<MarcaResponseDTO> getAllByNit(Long idNit, int page, int pageSize, Boolean isAscending);

    List<MarcaResponseDTO> getAllByNitFiltradoPorNome(Long idNit, String nome, int page, int pageSize, Boolean isAscending);

    List<MarcaResponseDTO> getAllPublico(int page, int pageSize, Boolean isAscending);

    List<MarcaResponseDTO> getAllPublicoFiltradoPorNome(String nome, int page, int pageSize, Boolean isAscending);
}
