package br.unitins.pibiti.service.propiedade_intelectual.propiedade_industrial.indicacao_geografica;


import br.unitins.pibiti.dto.propiedade_intelectual.propiedade_industrial.indicacao_geografica.IndicacaoGeograficaDTO;
import br.unitins.pibiti.dto.propiedade_intelectual.propiedade_industrial.indicacao_geografica.IndicacaoGeograficaResponseDTO;
import br.unitins.pibiti.enums.EspecieIndicacaoGeografica;
import br.unitins.pibiti.enums.NaturezaIndicacaoGeografica;
import br.unitins.pibiti.enums.TipoPropiedadeIntelectual;
import br.unitins.pibiti.model.IndicacaoGeografica;
import br.unitins.pibiti.model.Nit;
import br.unitins.pibiti.repository.IndicacaoGeograficaRepository;
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
public class IndicacaoGeograficaServiceImpl implements IndicacaoGeograficaService {

    @Inject
    NitRepository nitRepository;

    @Inject
    IndicacaoGeograficaRepository indicacaoGeograficaRepository;

    @Inject
    Validator validator;

    @Override
    public IndicacaoGeograficaResponseDTO getIndicacaoGeografica(Long id) {
        IndicacaoGeografica indicacaoGeografica = indicacaoGeograficaRepository.findById(id);

        if (indicacaoGeografica == null) throw new NotFoundException("IndicacaoGeografica não encontrada.");

        return new IndicacaoGeograficaResponseDTO(indicacaoGeografica);
    }

    @Override
    @Transactional
    public IndicacaoGeograficaResponseDTO cadastrar(IndicacaoGeograficaDTO indicacaoGeograficaDTO) {
        validar(indicacaoGeograficaDTO);

        Nit nit = nitRepository.findById(indicacaoGeograficaDTO.idNit());

        IndicacaoGeografica indicacaoGeografica = new IndicacaoGeografica();
        inserirDadosDTONaClasse(indicacaoGeograficaDTO, indicacaoGeografica);
        indicacaoGeografica.setTipoPropiedadeIntelectual(TipoPropiedadeIntelectual.PRIPIEDADE_INDUSTRIAL);
        indicacaoGeografica.setNit(nit);

        indicacaoGeograficaRepository.persist(indicacaoGeografica);
        return new IndicacaoGeograficaResponseDTO(indicacaoGeografica);
    }

    @Override
    @Transactional
    public IndicacaoGeograficaResponseDTO atualizar(String cnpj, Long idIndicacaoGeografica, IndicacaoGeograficaDTO indicacaoGeograficaDTO) {
        validar(indicacaoGeograficaDTO);

        IndicacaoGeografica indicacaoGeografica = indicacaoGeograficaRepository.findById(idIndicacaoGeografica);
        Nit nit = nitRepository.findByCnpj(cnpj);

        if (indicacaoGeografica == null) {
            throw new NotFoundException("Nenhuma indicação geográfica encontrada.");
        }

        if (indicacaoGeografica.getNit().getIdNit() != nit.getIdNit())
            throw new BadRequestException("A indicação geográfica selecionada não pertence ao NIT informado.");

        inserirDadosDTONaClasse(indicacaoGeograficaDTO, indicacaoGeografica);

        return new IndicacaoGeograficaResponseDTO(indicacaoGeografica);
    }

    private IndicacaoGeografica inserirDadosDTONaClasse(IndicacaoGeograficaDTO indicacaoGeograficaDTO, IndicacaoGeografica indicacaoGeografica) {
        indicacaoGeografica.setTitulo(indicacaoGeograficaDTO.titulo());
        indicacaoGeografica.setDescricao(indicacaoGeograficaDTO.descricao());
        indicacaoGeografica.setDataConcessao(indicacaoGeograficaDTO.dataConcessao());
        indicacaoGeografica.setEspecie(EspecieIndicacaoGeografica.fromId(indicacaoGeograficaDTO.idEspecie()));
        indicacaoGeografica.setNatureza(NaturezaIndicacaoGeografica.fromId(indicacaoGeograficaDTO.idNatureza()));
        indicacaoGeografica.setTituloProdutoServico(indicacaoGeograficaDTO.tituloProdutoServico());
        indicacaoGeografica.setDelimitacao(indicacaoGeograficaDTO.delimitacao());
        indicacaoGeografica.setRequerente(indicacaoGeograficaDTO.requerente());

        return indicacaoGeografica;
    }

    @Override
    @Transactional
    public void deletarIndicacaoGeografica(String cnpj, Long idIndicacaoGeografica) {
        Nit nit = nitRepository.findByCnpj(cnpj);
        IndicacaoGeografica indicacaoGeografica = indicacaoGeograficaRepository.findById(idIndicacaoGeografica);

        if (indicacaoGeografica == null) {
            throw new NotFoundException("Nenhuma indicação geográfica encontrada.");
        }

        if (indicacaoGeografica.getNit().getIdNit() != nit.getIdNit())
            throw new BadRequestException("A indicação geográfica selecionada não pertence ao NIT informado.");

        if (indicacaoGeograficaRepository.isPersistent(indicacaoGeografica))
            indicacaoGeograficaRepository.delete(indicacaoGeografica);

        else throw new NotFoundException("Nenhuma indicação geográfica encontrada.");
    }

    private void validar(IndicacaoGeograficaDTO indicacaoGeograficaDTO) throws ConstraintViolationException {

        Set<ConstraintViolation<IndicacaoGeograficaDTO>> violations = validator.validate(indicacaoGeograficaDTO);

        if (!violations.isEmpty()) throw new ConstraintViolationException(violations);
    }

}
