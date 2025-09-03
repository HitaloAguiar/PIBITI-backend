package br.unitins.pibiti.service.avaliacao_trl_pi;

import br.unitins.pibiti.dto.avaliacao_trl_pi.AvaliacaoTrlPiDTO;
import br.unitins.pibiti.dto.avaliacao_trl_pi.AvaliacaoTrlPiResponseDTO;
import br.unitins.pibiti.model.AvaliacaoTRLPropiedadeIntelectual;
import br.unitins.pibiti.model.ContratoFranquia;
import br.unitins.pibiti.model.DesenhoIndustrial;
import br.unitins.pibiti.model.DireitoAutor;
import br.unitins.pibiti.model.IndicacaoGeografica;
import br.unitins.pibiti.model.Marca;
import br.unitins.pibiti.model.Nit;
import br.unitins.pibiti.model.Patente;
import br.unitins.pibiti.model.RegistroProgramaComputador;
import br.unitins.pibiti.repository.AvaliacaoTrlPIRepository;
import br.unitins.pibiti.repository.ContratoFranquiaRepository;
import br.unitins.pibiti.repository.DesenhoIndustrialRepository;
import br.unitins.pibiti.repository.DireitoAutorRepository;
import br.unitins.pibiti.repository.IndicacaoGeograficaRepository;
import br.unitins.pibiti.repository.MarcaRepository;
import br.unitins.pibiti.repository.NitRepository;
import br.unitins.pibiti.repository.PatenteRepository;
import br.unitins.pibiti.repository.RegistroProgramaComputadorRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@ApplicationScoped
public class AvaliacaoTrlPropiedadeIntelectualServiceImpl implements AvaliacaoTrlPropiedadeIntelectualService {

    @Inject
    NitRepository nitRepository;

    @Inject
    AvaliacaoTrlPIRepository avaliacaoTrlPIRepository;

    @Inject
    MarcaRepository marcaRepository;

    @Inject
    PatenteRepository patenteRepository;

    @Inject
    ContratoFranquiaRepository contratoFranquiaRepository;

    @Inject
    DesenhoIndustrialRepository desenhoIndustrialRepository;

    @Inject
    IndicacaoGeograficaRepository indicacaoGeograficaRepository;

    @Inject
    DireitoAutorRepository direitoAutorRepository;

    @Inject
    RegistroProgramaComputadorRepository registroProgramaComputadorRepository;

    @Inject
    Validator validator;

    @Override
    public AvaliacaoTrlPiResponseDTO getAvaliacao(Long id) {
        AvaliacaoTRLPropiedadeIntelectual avaliacao = avaliacaoTrlPIRepository.findById(id);
        return new AvaliacaoTrlPiResponseDTO(avaliacao);
    }

    @Override
    @Transactional
    public AvaliacaoTrlPiResponseDTO cadastrar(AvaliacaoTrlPiDTO avaliacaoDTO) {
        validar(avaliacaoDTO);

        Marca marca = marcaRepository.findById(avaliacaoDTO.idMarca());
        Patente patente = patenteRepository.findById(avaliacaoDTO.idPatente());
        ContratoFranquia contratoFranquia = contratoFranquiaRepository.findById(avaliacaoDTO.idContratoFranquia());
        DesenhoIndustrial desenhoIndustrial = desenhoIndustrialRepository.findById(avaliacaoDTO.idDesenhoIndustrial());
        IndicacaoGeografica indicacaoGeografica = indicacaoGeograficaRepository.findById(avaliacaoDTO.idIndicacaoGeografica());
        DireitoAutor direitoAutor = direitoAutorRepository.findById(avaliacaoDTO.idDireitoAutor());
        RegistroProgramaComputador registroPrograma = registroProgramaComputadorRepository.findById(avaliacaoDTO.idRegistroProgramaComputador());

        AvaliacaoTRLPropiedadeIntelectual avaliacaoTRL = new AvaliacaoTRLPropiedadeIntelectual();

        if (marca != null) {
            avaliacaoTRL.setMarca(marca);
        } else if (patente != null) {
            avaliacaoTRL.setPatente(patente);
        } else if (contratoFranquia != null) {
            avaliacaoTRL.setContratoFranquia(contratoFranquia);
        } else if (desenhoIndustrial != null) {
            avaliacaoTRL.setDesenhoIndustrial(desenhoIndustrial);
        } else if (indicacaoGeografica != null) {
            avaliacaoTRL.setIndicacaoGeografica(indicacaoGeografica);
        } else if (direitoAutor != null) {
            avaliacaoTRL.setDireitoAutor(direitoAutor);
        } else if (registroPrograma != null) {
            avaliacaoTRL.setRegistroProgramaComputador(registroPrograma);
        } else {
            throw new NotFoundException("Nenhuma Propiedade Intelectual foi informada.");
        }

        LinkedHashMap<Integer, List<Boolean>> criteriosPorNivel = construirCriteriosPorNivel(avaliacaoDTO);
        int trl = calcularTrl(criteriosPorNivel);
        float trlScore = calcularTrlScore(criteriosPorNivel, trl);

        avaliacaoTRL.setTrl(trl);
        avaliacaoTRL.setTrlScore(trlScore);

        avaliacaoTrlPIRepository.persist(avaliacaoTRL);

        return new AvaliacaoTrlPiResponseDTO(avaliacaoTRL);
    }

    @Override
    @Transactional
    public void deletarAvaliacao(String cnpj, Long idAvaliacao) {
        Nit nit = nitRepository.findByCnpj(cnpj);
        AvaliacaoTRLPropiedadeIntelectual avaliacao = avaliacaoTrlPIRepository.findById(idAvaliacao);

        if (avaliacao == null) {
            throw new NotFoundException("Nenhuma avaliação encontrada.");
        }

        if (avaliacao.getMarca().getNit().getIdNit() != nit.getIdNit())
            throw new BadRequestException("A avaliação selecionada não pertence ao NIT informado.");

    }

    private void validar(AvaliacaoTrlPiDTO avaliacaoTrlPiDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<AvaliacaoTrlPiDTO>> violations = validator.validate(avaliacaoTrlPiDTO);

        if (!violations.isEmpty()) throw new ConstraintViolationException(violations);
    }

    private boolean preenchido(String valor) {
        return valor != null && !valor.isBlank();
    }

    private boolean verdadeiro(Boolean valor) {
        return Boolean.TRUE.equals(valor);
    }

    private boolean maiorQueZero(java.math.BigDecimal valor) {
        return valor != null && valor.compareTo(java.math.BigDecimal.ZERO) > 0;
    }

    private LinkedHashMap<Integer, List<Boolean>> construirCriteriosPorNivel(AvaliacaoTrlPiDTO dto) {
        LinkedHashMap<Integer, List<Boolean>> criteriosPorNivel = new LinkedHashMap<>();

        criteriosPorNivel.put(1, List.of(preenchido(dto.hipotese()), preenchido(dto.relatorio())));
        criteriosPorNivel.put(2, List.of(preenchido(dto.especificacoes()), verdadeiro(dto.designVerificationPlan())));
        criteriosPorNivel.put(3, List.of(preenchido(dto.protocolo()), preenchido(dto.resultados())));
        criteriosPorNivel.put(4, List.of(verdadeiro(dto.designVerificationTest()), preenchido(dto.tipoOuAmbienteTeste()), preenchido(dto.relatorio())));
        criteriosPorNivel.put(5, List.of(verdadeiro(dto.conformidades()), preenchido(dto.resultados())));
        criteriosPorNivel.put(6, List.of(verdadeiro(dto.proofOfConceptComCliente()), preenchido(dto.resultados())));
        criteriosPorNivel.put(7, List.of(verdadeiro(dto.contratoLicencaFornecimento()), preenchido(dto.resultados())));
        criteriosPorNivel.put(8, List.of(preenchido(dto.relatorio()), verdadeiro(dto.dossiesCertificacoes())));
        criteriosPorNivel.put(9, List.of(verdadeiro(dto.contratoLicencaFornecimento()), maiorQueZero(dto.receitaInicial())));

        return criteriosPorNivel;
    }

    private int calcularTrl(LinkedHashMap<Integer, List<Boolean>> gates) {
        int trl = 0;
        for (Map.Entry<Integer, List<Boolean>> entry : gates.entrySet()) {
            if (entry.getValue().stream().allMatch(Boolean.TRUE::equals)) {
                trl = entry.getKey();
            } else {
                break;
            }
        }
        return trl;
    }

    private float calcularTrlScore(LinkedHashMap<Integer, List<Boolean>> gates, int trl) {
        int total = 0, cumpridas = 0;
        for (Map.Entry<Integer, List<Boolean>> entry : gates.entrySet()) {
            if (entry.getKey() > trl) break;
            for (Boolean b : entry.getValue()) {
                total++;
                if (Boolean.TRUE.equals(b)) cumpridas++;
            }
        }
        return total == 0 ? 0f : (cumpridas * 100f) / (float) total;
    }

}
