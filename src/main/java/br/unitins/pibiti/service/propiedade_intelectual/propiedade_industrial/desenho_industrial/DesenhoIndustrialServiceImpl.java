package br.unitins.pibiti.service.propiedade_intelectual.propiedade_industrial.desenho_industrial;


import br.unitins.pibiti.dto.propiedade_intelectual.propiedade_industrial.desenho_industrial.DesenhoIndustrialDTO;
import br.unitins.pibiti.dto.propiedade_intelectual.propiedade_industrial.desenho_industrial.DesenhoIndustrialResponseDTO;
import br.unitins.pibiti.enums.TipoDesenhoIndustrial;
import br.unitins.pibiti.enums.TipoPropiedadeIntelectual;
import br.unitins.pibiti.model.DesenhoIndustrial;
import br.unitins.pibiti.model.Nit;
import br.unitins.pibiti.repository.DesenhoIndustrialRepository;
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
public class DesenhoIndustrialServiceImpl implements DesenhoIndustrialService {

    @Inject
    NitRepository nitRepository;

    @Inject
    DesenhoIndustrialRepository desenhoIndustrialRepository;

    @Inject
    Validator validator;

    @Override
    public DesenhoIndustrialResponseDTO getDesenhoIndustrial(Long id) {
        DesenhoIndustrial desenhoIndustrial = desenhoIndustrialRepository.findById(id);

        if (desenhoIndustrial == null) throw new NotFoundException("DesenhoIndustrial não encontrada.");

        return new DesenhoIndustrialResponseDTO(desenhoIndustrial);
    }

    @Override
    @Transactional
    public DesenhoIndustrialResponseDTO cadastrar(DesenhoIndustrialDTO desenhoIndustrialDTO) {
        validar(desenhoIndustrialDTO);

        Nit nit = nitRepository.findById(desenhoIndustrialDTO.idNit());

        DesenhoIndustrial desenhoIndustrial = new DesenhoIndustrial();
        inserirDadosDTONaClasse(desenhoIndustrialDTO, desenhoIndustrial);
        desenhoIndustrial.setTipoPropiedadeIntelectual(TipoPropiedadeIntelectual.PRIPIEDADE_INDUSTRIAL);
        desenhoIndustrial.setNit(nit);

        desenhoIndustrialRepository.persist(desenhoIndustrial);
        return new DesenhoIndustrialResponseDTO(desenhoIndustrial);
    }

    @Override
    @Transactional
    public DesenhoIndustrialResponseDTO atualizar(String cnpj, Long idDesenhoIndustrial, DesenhoIndustrialDTO desenhoIndustrialDTO) {
        validar(desenhoIndustrialDTO);

        DesenhoIndustrial desenhoIndustrial = desenhoIndustrialRepository.findById(idDesenhoIndustrial);
        Nit nit = nitRepository.findByCnpj(cnpj);

        if (desenhoIndustrial == null) {
            throw new NotFoundException("Nenhum desenho industrial encontrado.");
        }

        if (desenhoIndustrial.getNit().getIdNit() != nit.getIdNit())
            throw new BadRequestException("O desenho industrial selecionado não pertence ao NIT informado.");

        inserirDadosDTONaClasse(desenhoIndustrialDTO, desenhoIndustrial);

        return new DesenhoIndustrialResponseDTO(desenhoIndustrial);
    }

    private DesenhoIndustrial inserirDadosDTONaClasse(DesenhoIndustrialDTO desenhoIndustrialDTO, DesenhoIndustrial desenhoIndustrial) {
        desenhoIndustrial.setTitulo(desenhoIndustrialDTO.titulo());
        desenhoIndustrial.setDescricao(desenhoIndustrialDTO.descricao());
        desenhoIndustrial.setDataConcessao(desenhoIndustrialDTO.dataConcessao());
        desenhoIndustrial.setPeriodo(desenhoIndustrialDTO.periodo());
        desenhoIndustrial.setTipoDesenhoIndustrial(TipoDesenhoIndustrial.fromId(desenhoIndustrialDTO.idTipoDesenhoIndustrial()));

        return desenhoIndustrial;
    }

    @Override
    @Transactional
    public void deletarDesenhoIndustrial(String cnpj, Long idDesenhoIndustrial) {
        Nit nit = nitRepository.findByCnpj(cnpj);
        DesenhoIndustrial desenhoIndustrial = desenhoIndustrialRepository.findById(idDesenhoIndustrial);

        if (desenhoIndustrial == null) {
            throw new NotFoundException("Nenhum desenho industrial encontrado.");
        }

        if (desenhoIndustrial.getNit().getIdNit() != nit.getIdNit())
            throw new BadRequestException("O desenho industrial selecionado não pertence ao NIT informado.");

        if (desenhoIndustrialRepository.isPersistent(desenhoIndustrial))
            desenhoIndustrialRepository.delete(desenhoIndustrial);

        else throw new NotFoundException("Nenhuma desenho industrial encontrado.");
    }

    private void validar(DesenhoIndustrialDTO desenhoIndustrialDTO) throws ConstraintViolationException {

        Set<ConstraintViolation<DesenhoIndustrialDTO>> violations = validator.validate(desenhoIndustrialDTO);

        if (!violations.isEmpty()) throw new ConstraintViolationException(violations);
    }

}
