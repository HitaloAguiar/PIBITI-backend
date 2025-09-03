package br.unitins.pibiti.dto.avaliacao_trl_pi;

import br.unitins.pibiti.dto.propiedade_intelectual.direito_autoral.direito_autor.DireitoAutorResponseDTO;
import br.unitins.pibiti.dto.propiedade_intelectual.direito_autoral.registro_programa_computador.RegistroProgramaComputadorResponseDTO;
import br.unitins.pibiti.dto.propiedade_intelectual.propiedade_industrial.contato_franquia.ContratoFranquiaResponseDTO;
import br.unitins.pibiti.dto.propiedade_intelectual.propiedade_industrial.desenho_industrial.DesenhoIndustrialResponseDTO;
import br.unitins.pibiti.dto.propiedade_intelectual.propiedade_industrial.indicacao_geografica.IndicacaoGeograficaResponseDTO;
import br.unitins.pibiti.dto.propiedade_intelectual.propiedade_industrial.marca.MarcaResponseDTO;
import br.unitins.pibiti.dto.propiedade_intelectual.propiedade_industrial.patente.PatenteResponseDTO;
import br.unitins.pibiti.dto.propiedade_intelectual.protecao_sui_generis.cultivar.CultivarResponseDTO;
import br.unitins.pibiti.dto.propiedade_intelectual.protecao_sui_generis.topografia_circuito_integrado.TopografiaCircuitoIntegradoResponseDTO;
import br.unitins.pibiti.model.AvaliacaoTRLPropiedadeIntelectual;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record AvaliacaoTrlPiResponseDTO(Long idAvaliacaoTrlPI,

                                        // Propiedade Intelectual Relacionada
                                        MarcaResponseDTO marca, PatenteResponseDTO patente,
                                        ContratoFranquiaResponseDTO contratoFranquia,
                                        DesenhoIndustrialResponseDTO desenhoIndustrial,
                                        IndicacaoGeograficaResponseDTO indicacaoGeografica,
                                        DireitoAutorResponseDTO direitoAutor,
                                        RegistroProgramaComputadorResponseDTO registroProgramaComputador,
                                        CultivarResponseDTO cultivar,
                                        TopografiaCircuitoIntegradoResponseDTO topografiaCircuitoIntegrado,

                                        // Variáveis Científicas/Técnicas
                                        String hipotese, String protocolo, String relatorio, String especificacoes,
                                        Boolean designVerificationTest, Boolean designVerificationPlan,

                                        // Variáveis de PI & Jurídico
                                        String statusPi, Boolean freedomToOperate, Boolean nonDisclosureAgreement,
                                        Boolean materialTransferAgreement, Boolean licença,

                                        // Variáveis de Testes & Qualidade
                                        String tipoOuAmbienteTeste, String resultados, Boolean conformidades,

                                        // Variáveis de Mercado & Parcerias
                                        Boolean proofOfConceptComCliente, Boolean contratoLicençaFornecimento,
                                        BigDecimal receitaInicial,

                                        // Variáveis de Regulatório
                                        Boolean dossiesCertificacoes,

                                        // Cálculo TRL
                                        Integer trl, Float trlScore, LocalDateTime dataAvaliacao) {

    public AvaliacaoTrlPiResponseDTO(AvaliacaoTRLPropiedadeIntelectual avaliacao) {
        this(avaliacao.getIdAvaliacaoTrlPI(), new MarcaResponseDTO(avaliacao.getMarca()), new PatenteResponseDTO(avaliacao.getPatente()), new ContratoFranquiaResponseDTO(avaliacao.getContratoFranquia()), new DesenhoIndustrialResponseDTO(avaliacao.getDesenhoIndustrial()),
                new IndicacaoGeograficaResponseDTO(avaliacao.getIndicacaoGeografica()), new DireitoAutorResponseDTO(avaliacao.getDireitoAutor()), new RegistroProgramaComputadorResponseDTO(avaliacao.getRegistroProgramaComputador()),
                new CultivarResponseDTO(avaliacao.getCultivar()), new TopografiaCircuitoIntegradoResponseDTO(avaliacao.getTopografiaCircuitoIntegrado()),
                avaliacao.getHipotese(), avaliacao.getProtocolo(), avaliacao.getRelatorio(), avaliacao.getEspecificacoes(), avaliacao.getDesignVerificationTest(), avaliacao.getDesignVerificationPlan(), avaliacao.getStatusPi(), avaliacao.getFreedomToOperate(),
                avaliacao.getNonDisclosureAgreement(), avaliacao.getMaterialTransferAgreement(), avaliacao.getLicença(), avaliacao.getTipoOuAmbienteTeste(), avaliacao.getResultados(), avaliacao.getConformidades(), avaliacao.getProofOfConceptComCliente(),
                avaliacao.getContratoLicençaFornecimento(), avaliacao.getReceitaInicial(), avaliacao.getDossiesCertificacoes(), avaliacao.getTrl(), avaliacao.getTrlScore(), avaliacao.getCreatedAt());
    }

}
