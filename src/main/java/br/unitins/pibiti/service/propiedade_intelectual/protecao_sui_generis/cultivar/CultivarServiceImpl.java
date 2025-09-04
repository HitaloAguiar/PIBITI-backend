package br.unitins.pibiti.service.propiedade_intelectual.protecao_sui_generis.cultivar;


import br.unitins.pibiti.dto.propiedade_intelectual.protecao_sui_generis.cultivar.CultivarDTO;
import br.unitins.pibiti.dto.propiedade_intelectual.protecao_sui_generis.cultivar.CultivarResponseDTO;
import br.unitins.pibiti.enums.CategoriaCultivar;
import br.unitins.pibiti.enums.TipoPropiedadeIntelectual;
import br.unitins.pibiti.model.Cultivar;
import br.unitins.pibiti.model.Nit;
import br.unitins.pibiti.repository.CultivarRepository;
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
public class CultivarServiceImpl implements CultivarService {

    @Inject
    NitRepository nitRepository;

    @Inject
    CultivarRepository cultivarRepository;

    @Inject
    Validator validator;

    @Override
    public CultivarResponseDTO getCultivar(Long id) {
        Cultivar cultivar = cultivarRepository.findById(id);

        if (cultivar == null) throw new NotFoundException("Cultivar não encontrado.");

        return new CultivarResponseDTO(cultivar);
    }

    @Override
    @Transactional
    public CultivarResponseDTO cadastrar(CultivarDTO cultivarDTO) {
        validar(cultivarDTO);

        Nit nit = nitRepository.findById(cultivarDTO.idNit());

        Cultivar cultivar = new Cultivar();
        inserirDadosDTONaClasse(cultivarDTO, cultivar);
        cultivar.setTipoPropiedadeIntelectual(TipoPropiedadeIntelectual.PROTECAO_SUI_GENERIS);
        cultivar.setNit(nit);

        cultivarRepository.persist(cultivar);
        return new CultivarResponseDTO(cultivar);
    }

    @Override
    @Transactional
    public CultivarResponseDTO atualizar(String cnpj, Long idCultivar, CultivarDTO cultivarDTO) {
        validar(cultivarDTO);

        Cultivar cultivar = cultivarRepository.findById(idCultivar);
        Nit nit = nitRepository.findByCnpj(cnpj);

        if (cultivar == null) {
            throw new NotFoundException("Nenhum cultivar encontrado.");
        }

        if (cultivar.getNit().getIdNit() != nit.getIdNit())
            throw new BadRequestException("O cultivar selecionado não pertence ao NIT informado.");

        inserirDadosDTONaClasse(cultivarDTO, cultivar);

        return new CultivarResponseDTO(cultivar);
    }

    private Cultivar inserirDadosDTONaClasse(CultivarDTO cultivarDTO, Cultivar cultivar) {
        cultivar.setTitulo(cultivarDTO.titulo());
        cultivar.setDescricao(cultivarDTO.descricao());
        cultivar.setCategoria(CategoriaCultivar.fromId(cultivarDTO.idCategoria()));
        cultivar.setVisualizacaoPublica(cultivarDTO.visualizacaoPublica());

        return cultivar;
    }

    @Override
    @Transactional
    public void deletarCultivar(String cnpj, Long idCultivar) {
        Nit nit = nitRepository.findByCnpj(cnpj);
        Cultivar cultivar = cultivarRepository.findById(idCultivar);

        if (cultivar == null) {
            throw new NotFoundException("Nenhum cultivar encontrado.");
        }

        if (cultivar.getNit().getIdNit() != nit.getIdNit())
            throw new BadRequestException("O cultivar selecionado não pertence ao NIT informado.");

        if (cultivarRepository.isPersistent(cultivar))
            cultivarRepository.delete(cultivar);

        else throw new NotFoundException("Nenhuma cultivar encontrado.");
    }

    private void validar(CultivarDTO cultivarDTO) throws ConstraintViolationException {

        Set<ConstraintViolation<CultivarDTO>> violations = validator.validate(cultivarDTO);

        if (!violations.isEmpty()) throw new ConstraintViolationException(violations);
    }

}
