package br.unitins.pibiti.service.propriedade_intelectual.propriedade_industrial.desenho_industrial;


import br.unitins.pibiti.dto.propriedade_intelectual.propriedade_industrial.desenho_industrial.DesenhoIndustrialDTO;
import br.unitins.pibiti.dto.propriedade_intelectual.propriedade_industrial.desenho_industrial.DesenhoIndustrialResponseDTO;
import br.unitins.pibiti.enums.TipoDesenhoIndustrial;
import br.unitins.pibiti.enums.TipoPropriedadeIntelectual;
import br.unitins.pibiti.model.DesenhoIndustrial;
import br.unitins.pibiti.model.Nit;
import br.unitins.pibiti.repository.DesenhoIndustrialRepository;
import br.unitins.pibiti.repository.NitRepository;
import io.quarkus.panache.common.Sort;
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
        desenhoIndustrial.setTipoPropriedadeIntelectual(TipoPropriedadeIntelectual.PRIPRIEDADE_INDUSTRIAL);
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
        desenhoIndustrial.setTitular(desenhoIndustrialDTO.titular());
        desenhoIndustrial.setDescricao(desenhoIndustrialDTO.descricao());
        desenhoIndustrial.setDataConcessao(desenhoIndustrialDTO.dataConcessao());
        desenhoIndustrial.setPeriodo(desenhoIndustrialDTO.periodo());
        desenhoIndustrial.setTipoDesenhoIndustrial(TipoDesenhoIndustrial.fromId(desenhoIndustrialDTO.idTipoDesenhoIndustrial()));
        desenhoIndustrial.setVisualizacaoPublica(desenhoIndustrialDTO.visualizacaoPublica());

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

    @Override
    public List<DesenhoIndustrialResponseDTO> getAllDesenhoIndustrial(Long idNit, int page, int pageSize, Boolean isAscending) {

        Sort sort;

        if (isAscending) {

            sort = Sort.by("idDesenhoIndustrial").ascending();
        } else {

            sort = Sort.by("idDesenhoIndustrial").descending();
        }

        return desenhoIndustrialRepository.findListByNit(nitRepository.findById(idNit), sort).page(page, pageSize).list().stream().map(DesenhoIndustrialResponseDTO::new).toList();
    }

    @Override
    public List<DesenhoIndustrialResponseDTO> getAllFiltradoPorTitulo(Long idNit, String titulo, int page, int pageSize, Boolean isAscending) {

        Sort sort;

        if (isAscending) {

            sort = Sort.by("idDesenhoIndustrial").ascending();
        } else {

            sort = Sort.by("idDesenhoIndustrial").descending();
        }

        return desenhoIndustrialRepository.findListByNitAndTitulo(nitRepository.findById(idNit), titulo, sort).page(page, pageSize).list().stream().map(DesenhoIndustrialResponseDTO::new).toList();
    }

    private void validar(DesenhoIndustrialDTO desenhoIndustrialDTO) throws ConstraintViolationException {

        Set<ConstraintViolation<DesenhoIndustrialDTO>> violations = validator.validate(desenhoIndustrialDTO);

        if (!violations.isEmpty()) throw new ConstraintViolationException(violations);
    }

}
