package br.unitins.pibiti.service.propriedade_intelectual.direito_autoral.direito_autor;


import java.util.List;

import br.unitins.pibiti.dto.propriedade_intelectual.direito_autoral.direito_autor.DireitoAutorDTO;
import br.unitins.pibiti.dto.propriedade_intelectual.direito_autoral.direito_autor.DireitoAutorResponseDTO;

public interface DireitoAutorService {

    DireitoAutorResponseDTO getDireitoAutor(Long id);

    DireitoAutorResponseDTO cadastrar(DireitoAutorDTO direitoAutorDTO);

    DireitoAutorResponseDTO atualizar(String cnpj, Long idDireitoAutor, DireitoAutorDTO direitoAutorDTO);

    void deletarDireitoAutor(String cnpj, Long idDireitoAutor);

    List<DireitoAutorResponseDTO> getAllDireitoAutor(Long idNit, int page, int pageSize, Boolean isAscending);

    List<DireitoAutorResponseDTO> getAllFiltradoPorTitulo(Long idNit, String titulo, int page, int pageSize, Boolean isAscending);
}
