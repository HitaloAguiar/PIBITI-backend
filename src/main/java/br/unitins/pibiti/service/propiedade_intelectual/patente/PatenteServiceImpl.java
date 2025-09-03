package br.unitins.pibiti.service.propiedade_intelectual.patente;


import br.unitins.pibiti.dto.propiedade_intelectual.patente.PatenteDTO;
import br.unitins.pibiti.dto.propiedade_intelectual.patente.PatenteResponseDTO;
import br.unitins.pibiti.enums.TipoPatente;
import br.unitins.pibiti.enums.TipoPropiedadeIntelectual;
import br.unitins.pibiti.model.Nit;
import br.unitins.pibiti.model.Patente;
import br.unitins.pibiti.repository.NitRepository;
import br.unitins.pibiti.repository.PatenteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;

import java.util.Set;

@ApplicationScoped
public class PatenteServiceImpl implements PatenteService {

    @Inject
    NitRepository nitRepository;

    @Inject
    PatenteRepository patenteRepository;

    @Inject
    Validator validator;

    @Override
    public PatenteResponseDTO getPatente(Long id) {
        Patente patente = patenteRepository.findById(id);

        if (patente == null)
            throw new NotFoundException("Patente não encontrada.");

        return new PatenteResponseDTO(patente);
    }

    @Override
    @Transactional
    public PatenteResponseDTO cadastrar(PatenteDTO patenteDTO) {
        validar(patenteDTO);

        Nit nit = nitRepository.findById(patenteDTO.idNit());

        Patente patente = new Patente();
        inserirDadosDTONaClasse(patenteDTO, patente);
        patente.setTipoPropiedadeIntelectual(TipoPropiedadeIntelectual.PRIPIEDADE_INDUSTRIAL);
        patente.setNit(nit);

        patenteRepository.persist(patente);
        return new PatenteResponseDTO(patente);
    }

    @Override
    @Transactional
    public PatenteResponseDTO atualizar(String cnpj, Long idPatente, PatenteDTO patenteDTO) {
        validar(patenteDTO);

        Patente patente = patenteRepository.findById(idPatente);
        Nit nit = nitRepository.findByCnpj(cnpj);

        if (patente == null) {
            throw new NotFoundException("Nenhuma patente encontrada.");
        }

        if (patente.getNit().getIdNit() != nit.getIdNit())
            throw new BadRequestException("A patente selecionada não pertence ao NIT informado.");

        inserirDadosDTONaClasse(patenteDTO, patente);

        return new PatenteResponseDTO(patente);
    }

    private Patente inserirDadosDTONaClasse(PatenteDTO patenteDTO, Patente patente) {
        patente.setTitulo(patenteDTO.titulo());
        patente.setResumo(patenteDTO.resumo());
        patente.setDataConcessao(patenteDTO.dataConcessao());
        patente.setPeriodo(patenteDTO.periodo());
        patente.setTipo(TipoPatente.fromId(patenteDTO.idTipoPatente()));
        patente.setCategorias(patenteDTO.categorias());
        patente.setClassificacao(patenteDTO.classificacao());

        return patente;
    }

    @Override
    @Transactional
    public void deletarPatente(String cnpj, Long idPatente) {
        Nit nit = nitRepository.findByCnpj(cnpj);
        Patente patente = patenteRepository.findById(idPatente);

        if (patente == null) {
            throw new NotFoundException("Nenhuma patente encontrada.");
        }

        if (patente.getNit().getIdNit() != nit.getIdNit())
            throw new BadRequestException("A patente selecionada não pertence ao NIT informado.");

        if (patenteRepository.isPersistent(patente))
            patenteRepository.delete(patente);

        else
            throw new NotFoundException("Nenhuma patente encontrada.");
    }

    private void validar(PatenteDTO patenteDTO) throws ConstraintViolationException {

        Set<ConstraintViolation<PatenteDTO>> violations = validator.validate(patenteDTO);

        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);
    }

}
