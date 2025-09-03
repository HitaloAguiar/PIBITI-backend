package br.unitins.pibiti.service.propiedade_intelectual.marca;

import br.unitins.pibiti.dto.propiedade_intelectual.marca.MarcaDTO;
import br.unitins.pibiti.dto.propiedade_intelectual.marca.MarcaResponseDTO;
import br.unitins.pibiti.enums.NaturezaMarca;
import br.unitins.pibiti.enums.TipoPropiedadeIntelectual;
import br.unitins.pibiti.model.Marca;
import br.unitins.pibiti.model.Nit;
import br.unitins.pibiti.repository.MarcaRepository;
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
public class MarcaServiceImpl implements MarcaService {

    @Inject
    NitRepository nitRepository;

    @Inject
    MarcaRepository marcaRepository;

    @Inject
    Validator validator;

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
        marca.setNome(marcaDTO.nome());
        marca.setTitular(marcaDTO.titular());
        marca.setDataConcessao(marcaDTO.dataConcessao());
        marca.setPeriodo(marcaDTO.periodo());
        marca.setNatureza(NaturezaMarca.fromId(marcaDTO.idNatureza()));
        marca.setClasses(marcaDTO.classes());
        marca.setTipoPropiedadeIntelectual(TipoPropiedadeIntelectual.PRIPIEDADE_INDUSTRIAL);
        marca.setNit(nit);

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

        marca.setNome(marcaDTO.nome());
        marca.setTitular(marcaDTO.titular());
        marca.setDataConcessao(marcaDTO.dataConcessao());
        marca.setPeriodo(marcaDTO.periodo());
        marca.setNatureza(NaturezaMarca.fromId(marcaDTO.idNatureza()));
        marca.setClasses(marcaDTO.classes());

        return new MarcaResponseDTO(marca);
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

    private void validar(MarcaDTO marcaDTO) throws ConstraintViolationException {

        Set<ConstraintViolation<MarcaDTO>> violations = validator.validate(marcaDTO);

        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);
    }

}
