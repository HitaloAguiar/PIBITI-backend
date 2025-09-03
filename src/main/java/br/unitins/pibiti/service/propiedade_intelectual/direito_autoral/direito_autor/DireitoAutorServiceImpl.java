package br.unitins.pibiti.service.propiedade_intelectual.direito_autoral.direito_autor;


import br.unitins.pibiti.dto.propiedade_intelectual.direito_autoral.direito_autor.DireitoAutorDTO;
import br.unitins.pibiti.dto.propiedade_intelectual.direito_autoral.direito_autor.DireitoAutorResponseDTO;
import br.unitins.pibiti.enums.Genero;
import br.unitins.pibiti.enums.TipoPropiedadeIntelectual;
import br.unitins.pibiti.model.DireitoAutor;
import br.unitins.pibiti.model.Nit;
import br.unitins.pibiti.repository.DireitoAutorRepository;
import br.unitins.pibiti.repository.NitRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
import java.util.Set;

@ApplicationScoped
public class DireitoAutorServiceImpl implements DireitoAutorService {

    @Inject
    NitRepository nitRepository;

    @Inject
    DireitoAutorRepository direitoAutorRepository;

    @Inject
    Validator validator;

    @Override
    public DireitoAutorResponseDTO getDireitoAutor(Long id) {
        DireitoAutor direitoAutor = direitoAutorRepository.findById(id);

        if (direitoAutor == null) throw new NotFoundException("Direito de Autor não encontrada.");

        return new DireitoAutorResponseDTO(direitoAutor);
    }

    @Override
    @Transactional
    public DireitoAutorResponseDTO cadastrar(DireitoAutorDTO direitoAutorDTO) {
        validar(direitoAutorDTO);

        Nit nit = nitRepository.findById(direitoAutorDTO.idNit());

        DireitoAutor direitoAutor = new DireitoAutor();
        inserirDadosDTONaClasse(direitoAutorDTO, direitoAutor);
        direitoAutor.setTipoPropiedadeIntelectual(TipoPropiedadeIntelectual.DIREITO_AUTORAL);
        direitoAutor.setNit(nit);

        direitoAutorRepository.persist(direitoAutor);
        return new DireitoAutorResponseDTO(direitoAutor);
    }

    @Override
    @Transactional
    public DireitoAutorResponseDTO atualizar(String cnpj, Long idDireitoAutor, DireitoAutorDTO direitoAutorDTO) {
        validar(direitoAutorDTO);

        DireitoAutor direitoAutor = direitoAutorRepository.findById(idDireitoAutor);
        Nit nit = nitRepository.findByCnpj(cnpj);

        if (direitoAutor == null) {
            throw new NotFoundException("Nenhum direito de autor encontrado.");
        }

        if (direitoAutor.getNit().getIdNit() != nit.getIdNit())
            throw new BadRequestException("O direito de autor selecionado não pertence ao NIT informado.");

        inserirDadosDTONaClasse(direitoAutorDTO, direitoAutor);

        return new DireitoAutorResponseDTO(direitoAutor);
    }

    private DireitoAutor inserirDadosDTONaClasse(DireitoAutorDTO direitoAutorDTO, DireitoAutor direitoAutor) {
        direitoAutor.setTitulo(direitoAutorDTO.titulo());
        direitoAutor.setDescricao(direitoAutorDTO.descricao());
        direitoAutor.setAutores(direitoAutorDTO.autores());

        if (direitoAutorDTO.idsGenero() != null && !direitoAutorDTO.idsGenero().isEmpty()) {
            List<Genero> generos = direitoAutorDTO.idsGenero().stream()
                    .map(Genero::fromId)
                    .toList();
            direitoAutor.setGeneros(generos);
        } else {
            direitoAutor.setGeneros(List.of());
        }

        direitoAutor.setNumeroTotalPaginasObra(direitoAutorDTO.numeroTotalPaginasObra());
        direitoAutor.setAdapatacaoOuTraducao(direitoAutorDTO.adapatacaoOuTraducao());
        direitoAutor.setTituloObraOriginal(direitoAutorDTO.tituloObraOriginal());
        direitoAutor.setAutoresObraOriginal(direitoAutorDTO.autoresObraOriginal());

        return direitoAutor;
    }

    @Override
    @Transactional
    public void deletarDireitoAutor(String cnpj, Long idDireitoAutor) {
        Nit nit = nitRepository.findByCnpj(cnpj);
        DireitoAutor direitoAutor = direitoAutorRepository.findById(idDireitoAutor);

        if (direitoAutor == null) {
            throw new NotFoundException("Nenhum direito de autor encontrado.");
        }

        if (direitoAutor.getNit().getIdNit() != nit.getIdNit())
            throw new BadRequestException("O direito de autor selecionado não pertence ao NIT informado.");

        if (direitoAutorRepository.isPersistent(direitoAutor))
            direitoAutorRepository.delete(direitoAutor);

        else throw new NotFoundException("Nenhuma direito de autor encontrado.");
    }

    private void validar(DireitoAutorDTO direitoAutorDTO) throws ConstraintViolationException {

        Set<ConstraintViolation<DireitoAutorDTO>> violations = validator.validate(direitoAutorDTO);

        if (!violations.isEmpty()) throw new ConstraintViolationException(violations);
    }

}
