package br.unitins.pibiti.service.propriedade_intelectual.propriedade_industrial.indicacao_geografica;


import java.util.List;
import java.util.Set;

import br.unitins.pibiti.dto.propriedade_intelectual.propriedade_industrial.indicacao_geografica.IndicacaoGeograficaDTO;
import br.unitins.pibiti.dto.propriedade_intelectual.propriedade_industrial.indicacao_geografica.IndicacaoGeograficaResponseDTO;
import br.unitins.pibiti.enums.EspecieIndicacaoGeografica;
import br.unitins.pibiti.enums.NaturezaIndicacaoGeografica;
import br.unitins.pibiti.enums.TipoPropriedadeIntelectual;
import br.unitins.pibiti.model.IndicacaoGeografica;
import br.unitins.pibiti.model.Nit;
import br.unitins.pibiti.repository.IndicacaoGeograficaRepository;
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
        indicacaoGeografica.setTipoPropriedadeIntelectual(TipoPropriedadeIntelectual.PRIPRIEDADE_INDUSTRIAL);
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
        indicacaoGeografica.setVisualizacaoPublica(indicacaoGeograficaDTO.visualizacaoPublica());

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

    @Override
    public List<IndicacaoGeograficaResponseDTO> getAllByNit(Long idNit, int page, int pageSize, Boolean isAscending) {

        Sort sort;

        if (isAscending) {

            sort = Sort.by("idIndicacaoGeografica").ascending();
        } else {

            sort = Sort.by("idIndicacaoGeografica").descending();
        }

        return indicacaoGeograficaRepository.findListByNit(nitRepository.findById(idNit), sort).page(page, pageSize).list().stream().map(IndicacaoGeograficaResponseDTO::new).toList();
    }

    @Override
    public List<IndicacaoGeograficaResponseDTO> getAllByNitFiltradoPorTitulo(Long idNit, String titulo, int page, int pageSize, Boolean isAscending) {

        Sort sort;

        if (isAscending) {

            sort = Sort.by("idIndicacaoGeografica").ascending();
        } else {

            sort = Sort.by("idIndicacaoGeografica").descending();
        }

        return indicacaoGeograficaRepository.findListByNitAndTitulo(nitRepository.findById(idNit), titulo, sort).page(page, pageSize).list().stream().map(IndicacaoGeograficaResponseDTO::new).toList();
    }

    @Override
    public List<IndicacaoGeograficaResponseDTO> getAllPublico(int page, int pageSize, Boolean isAscending) {

        Sort sort;

        if (isAscending) {

            sort = Sort.by("idIndicacaoGeografica").ascending();
        } else {

            sort = Sort.by("idIndicacaoGeografica").descending();
        }

        return indicacaoGeograficaRepository.findAllPublico(sort).page(page, pageSize).list().stream().map(IndicacaoGeograficaResponseDTO::new).toList();
    }

    @Override
    public List<IndicacaoGeograficaResponseDTO> getAllPublicoFiltradoPorTitulo(String titulo, int page, int pageSize, Boolean isAscending) {

        Sort sort;

        if (isAscending) {

            sort = Sort.by("idIndicacaoGeografica").ascending();
        } else {

            sort = Sort.by("idIndicacaoGeografica").descending();
        }

        return indicacaoGeograficaRepository.findAllPublicoFiltradoTitulo(sort, titulo).page(page, pageSize).list().stream().map(IndicacaoGeograficaResponseDTO::new).toList();
    }

    private void validar(IndicacaoGeograficaDTO indicacaoGeograficaDTO) throws ConstraintViolationException {

        Set<ConstraintViolation<IndicacaoGeograficaDTO>> violations = validator.validate(indicacaoGeograficaDTO);

        if (!violations.isEmpty()) throw new ConstraintViolationException(violations);
    }

}
