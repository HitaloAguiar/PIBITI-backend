package br.unitins.pibiti.service.propiedade_intelectual.contrato_franquia;


import br.unitins.pibiti.dto.propiedade_intelectual.contato_franquia.ContratoFranquiaDTO;
import br.unitins.pibiti.dto.propiedade_intelectual.contato_franquia.ContratoFranquiaResponseDTO;
import br.unitins.pibiti.enums.TipoPropiedadeIntelectual;
import br.unitins.pibiti.model.ContratoFranquia;
import br.unitins.pibiti.model.Nit;
import br.unitins.pibiti.repository.ContratoFranquiaRepository;
import br.unitins.pibiti.repository.NitRepository;
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
public class ContratoFranquiaServiceImpl implements ContratoFranquiaService {

    @Inject
    NitRepository nitRepository;

    @Inject
    ContratoFranquiaRepository contratoFranquiaRepository;

    @Inject
    Validator validator;

    @Override
    public ContratoFranquiaResponseDTO getContratoFranquia(Long id) {
        ContratoFranquia contratoFranquia = contratoFranquiaRepository.findById(id);

        if (contratoFranquia == null)
            throw new NotFoundException("ContratoFranquia não encontrada.");

        return new ContratoFranquiaResponseDTO(contratoFranquia);
    }

    @Override
    @Transactional
    public ContratoFranquiaResponseDTO cadastrar(ContratoFranquiaDTO contratoFranquiaDTO) {
        validar(contratoFranquiaDTO);

        Nit nit = nitRepository.findById(contratoFranquiaDTO.idNit());

        ContratoFranquia contratoFranquia = new ContratoFranquia();
        inserirDadosDTONaClasse(contratoFranquiaDTO, contratoFranquia);
        contratoFranquia.setTipoPropiedadeIntelectual(TipoPropiedadeIntelectual.PRIPIEDADE_INDUSTRIAL);
        contratoFranquia.setNit(nit);

        contratoFranquiaRepository.persist(contratoFranquia);
        return new ContratoFranquiaResponseDTO(contratoFranquia);
    }

    @Override
    @Transactional
    public ContratoFranquiaResponseDTO atualizar(String cnpj, Long idContratoFranquia, ContratoFranquiaDTO contratoFranquiaDTO) {
        validar(contratoFranquiaDTO);

        ContratoFranquia contratoFranquia = contratoFranquiaRepository.findById(idContratoFranquia);
        Nit nit = nitRepository.findByCnpj(cnpj);

        if (contratoFranquia == null) {
            throw new NotFoundException("Nenhuma Contrato/Franquia encontrada.");
        }

        if (contratoFranquia.getNit().getIdNit() != nit.getIdNit())
            throw new BadRequestException("A Contrato/Franquia selecionada não pertence ao NIT informado.");

        inserirDadosDTONaClasse(contratoFranquiaDTO, contratoFranquia);

        return new ContratoFranquiaResponseDTO(contratoFranquia);
    }

    private ContratoFranquia inserirDadosDTONaClasse(ContratoFranquiaDTO contratoFranquiaDTO, ContratoFranquia contratoFranquia) {
        contratoFranquia.setTitulo(contratoFranquiaDTO.titulo());
        contratoFranquia.setDescricao(contratoFranquiaDTO.descricao());

        return contratoFranquia;
    }

    @Override
    @Transactional
    public void deletarContratoFranquia(String cnpj, Long idContratoFranquia) {
        Nit nit = nitRepository.findByCnpj(cnpj);
        ContratoFranquia contratoFranquia = contratoFranquiaRepository.findById(idContratoFranquia);

        if (contratoFranquia == null) {
            throw new NotFoundException("Nenhuma Contrato/Franquia encontrada.");
        }

        if (contratoFranquia.getNit().getIdNit() != nit.getIdNit())
            throw new BadRequestException("A Contrato/Franquia selecionada não pertence ao NIT informado.");

        if (contratoFranquiaRepository.isPersistent(contratoFranquia))
            contratoFranquiaRepository.delete(contratoFranquia);

        else
            throw new NotFoundException("Nenhuma Contrato/Franquia encontrada.");
    }

    private void validar(ContratoFranquiaDTO contratoFranquiaDTO) throws ConstraintViolationException {

        Set<ConstraintViolation<ContratoFranquiaDTO>> violations = validator.validate(contratoFranquiaDTO);

        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);
    }

}
