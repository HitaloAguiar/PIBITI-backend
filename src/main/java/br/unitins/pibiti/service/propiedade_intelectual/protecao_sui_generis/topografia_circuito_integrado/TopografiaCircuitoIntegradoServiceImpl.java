package br.unitins.pibiti.service.propiedade_intelectual.protecao_sui_generis.topografia_circuito_integrado;


import br.unitins.pibiti.dto.propiedade_intelectual.protecao_sui_generis.topografia_circuito_integrado.TopografiaCircuitoIntegradoDTO;
import br.unitins.pibiti.dto.propiedade_intelectual.protecao_sui_generis.topografia_circuito_integrado.TopografiaCircuitoIntegradoResponseDTO;
import br.unitins.pibiti.enums.TipoPropiedadeIntelectual;
import br.unitins.pibiti.model.Nit;
import br.unitins.pibiti.model.TopografiaCircuitoIntegrado;
import br.unitins.pibiti.repository.NitRepository;
import br.unitins.pibiti.repository.TopografiaCircuitoIntegradoRepository;
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
public class TopografiaCircuitoIntegradoServiceImpl implements TopografiaCircuitoIntegradoService {

    @Inject
    NitRepository nitRepository;

    @Inject
    TopografiaCircuitoIntegradoRepository topografiaCircuitoIntegradoRepository;

    @Inject
    Validator validator;

    @Override
    public TopografiaCircuitoIntegradoResponseDTO getTopografiaCircuitoIntegrado(Long id) {
        TopografiaCircuitoIntegrado topografiaCircuitoIntegrado = topografiaCircuitoIntegradoRepository.findById(id);

        if (topografiaCircuitoIntegrado == null)
            throw new NotFoundException("Topografia de Circuito Integrado não encontrado.");

        return new TopografiaCircuitoIntegradoResponseDTO(topografiaCircuitoIntegrado);
    }

    @Override
    @Transactional
    public TopografiaCircuitoIntegradoResponseDTO cadastrar(TopografiaCircuitoIntegradoDTO topografiaCircuitoIntegradoDTO) {
        validar(topografiaCircuitoIntegradoDTO);

        Nit nit = nitRepository.findById(topografiaCircuitoIntegradoDTO.idNit());

        TopografiaCircuitoIntegrado topografiaCircuitoIntegrado = new TopografiaCircuitoIntegrado();
        inserirDadosDTONaClasse(topografiaCircuitoIntegradoDTO, topografiaCircuitoIntegrado);
        topografiaCircuitoIntegrado.setTipoPropiedadeIntelectual(TipoPropiedadeIntelectual.PROTECAO_SUI_GENERIS);
        topografiaCircuitoIntegrado.setNit(nit);

        topografiaCircuitoIntegradoRepository.persist(topografiaCircuitoIntegrado);
        return new TopografiaCircuitoIntegradoResponseDTO(topografiaCircuitoIntegrado);
    }

    @Override
    @Transactional
    public TopografiaCircuitoIntegradoResponseDTO atualizar(String cnpj, Long idTopografiaCircuitoIntegrado, TopografiaCircuitoIntegradoDTO topografiaCircuitoIntegradoDTO) {
        validar(topografiaCircuitoIntegradoDTO);

        TopografiaCircuitoIntegrado topografiaCircuitoIntegrado = topografiaCircuitoIntegradoRepository.findById(idTopografiaCircuitoIntegrado);

        Nit nit = nitRepository.findByCnpj(cnpj);

        if (topografiaCircuitoIntegrado == null) {
            throw new NotFoundException("Nenhuma topografia de circuito integrado encontrada.");
        }

        if (topografiaCircuitoIntegrado.getNit().getIdNit() != nit.getIdNit())
            throw new BadRequestException("A topografia de circuito integrado selecionada não pertence ao NIT informado.");

        inserirDadosDTONaClasse(topografiaCircuitoIntegradoDTO, topografiaCircuitoIntegrado);

        return new TopografiaCircuitoIntegradoResponseDTO(topografiaCircuitoIntegrado);
    }

    private TopografiaCircuitoIntegrado inserirDadosDTONaClasse(TopografiaCircuitoIntegradoDTO topografiaCircuitoIntegradoDTO, TopografiaCircuitoIntegrado topografiaCircuitoIntegrado) {
        topografiaCircuitoIntegrado.setTitulo(topografiaCircuitoIntegradoDTO.titulo());
        topografiaCircuitoIntegrado.setDescricao(topografiaCircuitoIntegradoDTO.descricao());
        topografiaCircuitoIntegrado.setAutores(topografiaCircuitoIntegradoDTO.autores());

        return topografiaCircuitoIntegrado;
    }

    @Override
    @Transactional
    public void deletarTopografiaCircuitoIntegrado(String cnpj, Long idTopografiaCircuitoIntegrado) {
        Nit nit = nitRepository.findByCnpj(cnpj);
        TopografiaCircuitoIntegrado topografiaCircuitoIntegrado = topografiaCircuitoIntegradoRepository.findById(idTopografiaCircuitoIntegrado);

        if (topografiaCircuitoIntegrado == null) {
            throw new NotFoundException("Nenhuma topografia de circuito integrado encontrada.");
        }

        if (topografiaCircuitoIntegrado.getNit().getIdNit() != nit.getIdNit())
            throw new BadRequestException("A topografia de circuito integrado selecionada não pertence ao NIT informado.");

        if (topografiaCircuitoIntegradoRepository.isPersistent(topografiaCircuitoIntegrado))
            topografiaCircuitoIntegradoRepository.delete(topografiaCircuitoIntegrado);

        else throw new NotFoundException("Nenhuma topografia de circuito integrado encontrada.");
    }

    private void validar(TopografiaCircuitoIntegradoDTO topografiaCircuitoIntegradoDTO) throws ConstraintViolationException {

        Set<ConstraintViolation<TopografiaCircuitoIntegradoDTO>> violations = validator.validate(topografiaCircuitoIntegradoDTO);

        if (!violations.isEmpty()) throw new ConstraintViolationException(violations);
    }

}
