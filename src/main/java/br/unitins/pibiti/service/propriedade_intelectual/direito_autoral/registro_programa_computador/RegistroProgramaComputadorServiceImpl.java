package br.unitins.pibiti.service.propriedade_intelectual.direito_autoral.registro_programa_computador;


import java.util.Collections;
import java.util.List;
import java.util.Set;

import br.unitins.pibiti.dto.propriedade_intelectual.direito_autoral.registro_programa_computador.RegistroProgramaComputadorDTO;
import br.unitins.pibiti.dto.propriedade_intelectual.direito_autoral.registro_programa_computador.RegistroProgramaComputadorResponseDTO;
import br.unitins.pibiti.enums.TipoPropriedadeIntelectual;
import br.unitins.pibiti.model.Nit;
import br.unitins.pibiti.model.RegistroProgramaComputador;
import br.unitins.pibiti.repository.NitRepository;
import br.unitins.pibiti.repository.RegistroProgramaComputadorRepository;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class RegistroProgramaComputadorServiceImpl implements RegistroProgramaComputadorService {

    @Inject
    NitRepository nitRepository;

    @Inject
    RegistroProgramaComputadorRepository registroProgramaRepository;

    @Inject
    Validator validator;

    @Override
    public RegistroProgramaComputadorResponseDTO getRegistroProgramaComputador(Long id) {
        RegistroProgramaComputador registroPrograma = registroProgramaRepository.findById(id);

        if (registroPrograma == null) throw new NotFoundException("Registro de Programa de Computador não encontrado.");

        return new RegistroProgramaComputadorResponseDTO(registroPrograma);
    }

    @Override
    @Transactional
    public RegistroProgramaComputadorResponseDTO cadastrar(RegistroProgramaComputadorDTO registroProgramaDTO) {
        validar(registroProgramaDTO);

        Nit nit = nitRepository.findById(registroProgramaDTO.idNit());

        RegistroProgramaComputador registroPrograma = new RegistroProgramaComputador();
        inserirDadosDTONaClasse(registroProgramaDTO, registroPrograma);
        registroPrograma.setTipoPropriedadeIntelectual(TipoPropriedadeIntelectual.DIREITO_AUTORAL);
        registroPrograma.setNit(nit);

        registroProgramaRepository.persist(registroPrograma);
        return new RegistroProgramaComputadorResponseDTO(registroPrograma);
    }

    @Override
    @Transactional
    public RegistroProgramaComputadorResponseDTO atualizar(String cnpj, Long idRegistroProgramaComputador, RegistroProgramaComputadorDTO registroProgramaDTO) {
        validar(registroProgramaDTO);

        RegistroProgramaComputador registroPrograma = registroProgramaRepository.findById(idRegistroProgramaComputador);
        Nit nit = nitRepository.findByCnpj(cnpj);

        if (registroPrograma == null) {
            throw new NotFoundException("Nenhum registro de programa de computador encontrado.");
        }

        if (registroPrograma.getNit().getIdNit() != nit.getIdNit())
            throw new BadRequestException("O registro de programa de computador selecionado não pertence ao NIT informado.");

        inserirDadosDTONaClasse(registroProgramaDTO, registroPrograma);

        return new RegistroProgramaComputadorResponseDTO(registroPrograma);
    }

    @Override
    public List<RegistroProgramaComputadorResponseDTO> getAllByNit(Long idNit, int page, int pageSize, Boolean isAscending) {
        Sort sort = isAscending ? Sort.by("idRegistroProgramaComputador").ascending()
                : Sort.by("idRegistroProgramaComputador").descending();

        Nit nit = nitRepository.findById(idNit);
        if (nit == null) {
            return Collections.emptyList();
        }

        PanacheQuery<RegistroProgramaComputador> query = registroProgramaRepository.findListByNit(nit, sort);
        if (query == null) {
            return Collections.emptyList();
        }

        return query.page(page - 1, pageSize)
                .list()
                .stream()
                .map(RegistroProgramaComputadorResponseDTO::new)
                .toList();
    }


    @Override
    public List<RegistroProgramaComputadorResponseDTO> getAllByNitFiltradoPorTitulo(Long idNit, String titulo, int page, int pageSize, Boolean isAscending) {

        Sort sort;

        if (isAscending) {

            sort = Sort.by("idRegistroProgramaComputador").ascending();
        } else {

            sort = Sort.by("idRegistroProgramaComputador").descending();
        }

        return registroProgramaRepository.findListByNitAndTitulo(nitRepository.findById(idNit), titulo, sort).page(page, pageSize).list().stream().map(RegistroProgramaComputadorResponseDTO::new).toList();
    }

    @Override
    public List<RegistroProgramaComputadorResponseDTO> getAllPublico(int page, int pageSize, Boolean isAscending) {

        Sort sort;

        if (isAscending) {

            sort = Sort.by("idRegistroProgramaComputador").ascending();
        } else {

            sort = Sort.by("idRegistroProgramaComputador").descending();
        }

        return registroProgramaRepository.findAllPublico(sort).page(page, pageSize).list().stream().map(RegistroProgramaComputadorResponseDTO::new).toList();
    }

    @Override
    public List<RegistroProgramaComputadorResponseDTO> getAllPublicoFiltradoPorTitulo(String titulo, int page, int pageSize, Boolean isAscending) {

        Sort sort;

        if (isAscending) {

            sort = Sort.by("idRegistroProgramaComputador").ascending();
        } else {

            sort = Sort.by("idRegistroProgramaComputador").descending();
        }

        return registroProgramaRepository.findAllPublicoFiltradoTitulo(sort, titulo).page(page, pageSize).list().stream().map(RegistroProgramaComputadorResponseDTO::new).toList();
    }

    private RegistroProgramaComputador inserirDadosDTONaClasse(RegistroProgramaComputadorDTO registroProgramaDTO, RegistroProgramaComputador registroPrograma) {
        registroPrograma.setTitulo(registroProgramaDTO.titulo());
        registroPrograma.setDescricao(registroProgramaDTO.descricao());
        registroPrograma.setAutores(registroProgramaDTO.autores());
        registroPrograma.setLinguagens(registroProgramaDTO.linguagens());
        registroPrograma.setTipoPrograma(registroProgramaDTO.tipoPrograma());
        registroPrograma.setCampoAplicacao(registroProgramaDTO.campoAplicacao());
        registroPrograma.setVisualizacaoPublica(registroProgramaDTO.visualizacaoPublica());

        return registroPrograma;
    }

    @Override
    @Transactional
    public void deletarRegistroProgramaComputador(String cnpj, Long idRegistroProgramaComputador) {
        Nit nit = nitRepository.findByCnpj(cnpj);
        RegistroProgramaComputador registroPrograma = registroProgramaRepository.findById(idRegistroProgramaComputador);

        if (registroPrograma == null) {
            throw new NotFoundException("Nenhum registro de programa de computador encontrado.");
        }

        if (registroPrograma.getNit().getIdNit() != nit.getIdNit())
            throw new BadRequestException("O registro de programa de computador selecionado não pertence ao NIT informado.");

        if (registroProgramaRepository.isPersistent(registroPrograma))
            registroProgramaRepository.delete(registroPrograma);

        else throw new NotFoundException("Nenhuma registro de programa de computador encontrado.");
    }

    private void validar(RegistroProgramaComputadorDTO registroProgramaDTO) throws ConstraintViolationException {

        Set<ConstraintViolation<RegistroProgramaComputadorDTO>> violations = validator.validate(registroProgramaDTO);

        if (!violations.isEmpty()) throw new ConstraintViolationException(violations);
    }

}
