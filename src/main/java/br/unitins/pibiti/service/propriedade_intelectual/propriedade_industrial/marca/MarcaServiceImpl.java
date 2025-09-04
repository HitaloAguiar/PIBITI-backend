package br.unitins.pibiti.service.propriedade_intelectual.propriedade_industrial.marca;

import br.unitins.pibiti.dto.propriedade_intelectual.propriedade_industrial.marca.MarcaDTO;
import br.unitins.pibiti.dto.propriedade_intelectual.propriedade_industrial.marca.MarcaResponseDTO;
import br.unitins.pibiti.enums.NaturezaMarca;
import br.unitins.pibiti.enums.TipoPropriedadeIntelectual;
import br.unitins.pibiti.model.Marca;
import br.unitins.pibiti.model.Nit;
import br.unitins.pibiti.repository.MarcaRepository;
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
public class MarcaServiceImpl implements MarcaService {

    @Inject
    NitRepository nitRepository;

    @Inject
    MarcaRepository marcaRepository;

    @Inject
    Validator validator;

    Sort sort = Sort.by("idMarca").ascending();

    @Override
    public MarcaResponseDTO getMarca(Long id) {
        Marca marca = marcaRepository.findById(id);

        if (marca == null)
            throw new NotFoundException("Marca não encontrada.");

        return new MarcaResponseDTO(marca);
    }

    @Override
    @Transactional
    public MarcaResponseDTO cadastrar(MarcaDTO marcaDTO) {
        validar(marcaDTO);

        Nit nit = nitRepository.findById(marcaDTO.idNit());

        Marca marca = new Marca();
        marca.setTipoPropriedadeIntelectual(TipoPropriedadeIntelectual.PRIPRIEDADE_INDUSTRIAL);
        marca.setNit(nit);

        inserirDadosDTONaClasse(marcaDTO, marca);

        marcaRepository.persist(marca);
        return new MarcaResponseDTO(marca);
    }

    @Override
    @Transactional
    public MarcaResponseDTO atualizar(String cnpj, Long idMarca, MarcaDTO marcaDTO) {
        validar(marcaDTO);

        Marca marca = marcaRepository.findById(idMarca);
        Nit nit = nitRepository.findByCnpj(cnpj);

        if (marca == null) {
            throw new NotFoundException("Nenhuma marca encontrada.");
        }

        if (marca.getNit().getIdNit() != nit.getIdNit())
            throw new BadRequestException("A marca selecionada não pertence ao NIT informado.");

        inserirDadosDTONaClasse(marcaDTO, marca);

        return new MarcaResponseDTO(marca);
    }

    private Marca inserirDadosDTONaClasse(MarcaDTO marcaDTO, Marca marca) {
        marca.setNome(marcaDTO.nome());
        marca.setTitular(marcaDTO.titular());
        marca.setDataConcessao(marcaDTO.dataConcessao());
        marca.setPeriodo(marcaDTO.periodo());
        marca.setNatureza(NaturezaMarca.fromId(marcaDTO.idNatureza()));
        marca.setClasses(marcaDTO.classes());
        marca.setVisualizacaoPublica(marcaDTO.visualizacaoPublica());

        return marca;
    }

    @Override
    @Transactional
    public void deletarMarca(String cnpj, Long idMarca) {
        Nit nit = nitRepository.findByCnpj(cnpj);
        Marca marca = marcaRepository.findById(idMarca);

        if (marca == null) {
            throw new NotFoundException("Nenhuma marca encontrada.");
        }

        if (marca.getNit().getIdNit() != nit.getIdNit())
            throw new BadRequestException("A marca selecionada não pertence ao NIT informado.");

        if (marcaRepository.isPersistent(marca))
            marcaRepository.delete(marca);

        else
            throw new NotFoundException("Nenhuma marca encontrada.");
    }

    @Override
    public List<MarcaResponseDTO> getAllMarca(Long idNit, int page, int pageSize) {

        return marcaRepository.findListByNit(nitRepository.findById(idNit), sort).page(page, pageSize).list().stream().map(MarcaResponseDTO::new).toList();
    }

    @Override
    public List<MarcaResponseDTO> getAllFiltradoPorNome(Long idNit, String nome, int page, int pageSize) {

        return marcaRepository.findListByNitAndNome(nitRepository.findById(idNit), nome, sort).page(page, pageSize).list().stream().map(MarcaResponseDTO::new).toList();
    }

    private void validar(MarcaDTO marcaDTO) throws ConstraintViolationException {

        Set<ConstraintViolation<MarcaDTO>> violations = validator.validate(marcaDTO);

        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);
    }

}
