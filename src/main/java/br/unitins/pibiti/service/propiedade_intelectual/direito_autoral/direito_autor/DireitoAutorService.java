package br.unitins.pibiti.service.propiedade_intelectual.direito_autoral.direito_autor;


import br.unitins.pibiti.dto.propiedade_intelectual.direito_autoral.direito_autor.DireitoAutorDTO;
import br.unitins.pibiti.dto.propiedade_intelectual.direito_autoral.direito_autor.DireitoAutorResponseDTO;

public interface DireitoAutorService {

    DireitoAutorResponseDTO getDireitoAutor(Long id);

    DireitoAutorResponseDTO cadastrar(DireitoAutorDTO direitoAutorDTO);

    DireitoAutorResponseDTO atualizar(String cnpj, Long idDireitoAutor, DireitoAutorDTO direitoAutorDTO);

    void deletarDireitoAutor(String cnpj, Long idDireitoAutor);

}
