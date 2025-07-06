package br.unitins.pibiti.service.nit;

import java.util.Set;

import br.unitins.pibiti.dto.nit.NitDTO;
import br.unitins.pibiti.dto.nit.NitResponseDTO;
import br.unitins.pibiti.dto.responsavel.ResponsavelDTO;
import br.unitins.pibiti.model.Nit;
import br.unitins.pibiti.model.Responsavel;
import br.unitins.pibiti.repository.NitRepository;
import br.unitins.pibiti.repository.ResponsavelRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class NitServiceImpl implements NitService {
    
    @Inject
    NitRepository nitRepository;

    @Inject
    ResponsavelRepository responsavelRepository;

    @Inject
    Validator validator;

    @Override
    public NitResponseDTO getNit(Long id) {

        Nit nit = nitRepository.findById(id);

        if (nit == null)
            throw new NotFoundException("Nit não encontrado");

        return new NitResponseDTO(nit);
    }

    @Override
    @Transactional
    public NitResponseDTO cadastrar(NitDTO nitDTO) throws ConstraintViolationException {

        validar(nitDTO);
        validar(nitDTO.responsavelDTO());

        Nit nit = new Nit();

        nit.setCnpj(nitDTO.cnpj());

        nit.setEmail(nitDTO.email());

        nit.setTelefone(nitDTO.telefone());

        nit.setAnoInicioAtividades(nitDTO.anoInicioAtividades());

        nit.setIct(nitDTO.ict());

        nit.setPrivacidade(nitDTO.privacidade());
        
        nitRepository.persist(nit);

        Responsavel responsavel = new Responsavel(nitDTO.responsavelDTO());        
        
        responsavel.setNit(nit);

        responsavelRepository.persist(responsavel);
        
        nit.setResponsavel(responsavel);

        return new NitResponseDTO(nit);
    }

    @Override
    @Transactional
    public NitResponseDTO atualizar(Long id, NitDTO nitDTO) throws ConstraintViolationException {

        validar(nitDTO);
        validar(nitDTO.responsavelDTO());

        Nit nit = nitRepository.findById(id);

        nit.setCnpj(nitDTO.cnpj());

        nit.setEmail(nitDTO.email());

        nit.setTelefone(nitDTO.telefone());

        nit.setAnoInicioAtividades(nitDTO.anoInicioAtividades());

        nit.setIct(nitDTO.ict());

        nit.setPrivacidade(nitDTO.privacidade());

        Responsavel responsavel = responsavelRepository.findById(nit.getResponsavel().getIdResponsavel());

        nit.setResponsavel(atualizarResponsavel(responsavel, nitDTO.responsavelDTO()));

        return new NitResponseDTO(nit);
    }

    private Responsavel atualizarResponsavel(Responsavel responsavel, ResponsavelDTO responsavelDTO) {

        responsavel.setNomeCompleto(responsavelDTO.nomeCompleto());

        responsavel.setCpf(responsavelDTO.cpf());

        responsavel.setEmail(responsavelDTO.email());

        responsavel.setTelefone(responsavelDTO.telefone());

        responsavel.setCargo(responsavelDTO.cargo());

        return responsavel;
    }

    private void validar(NitDTO nitDTO) throws ConstraintViolationException {

        Set<ConstraintViolation<NitDTO>> violations = validator.validate(nitDTO);

        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);
    }

    private void validar(ResponsavelDTO responsavelDTO) throws ConstraintViolationException {

        Set<ConstraintViolation<ResponsavelDTO>> violations = validator.validate(responsavelDTO);

        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);
    }
}
