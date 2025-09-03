package br.unitins.pibiti.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "avaliacao_trl_propiedade_intelectual")
public class AvaliacaoTRLPropiedadeIntelectual extends DefaultEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_avaliacao_trl_propiedade_intelectual")
    private Long idAvaliacaoTrlPI;

    // Propiedade Intelectual relacionada - Deve ser só uma delas
    @ManyToOne
    @JoinColumn(name = "id_marca")
    private Marca marca;

    @ManyToOne
    @JoinColumn(name = "id_patente")
    private Patente patente;

    @ManyToOne
    @JoinColumn(name = "id_contrato_franquia")
    private ContratoFranquia contratoFranquia;

    // Variáveis Científicas/Técnicas
    private String hipotese;
    private String protocolo;
    private String relatorio;
    private String especificacoes;
    private Boolean designVerificationTest;
    private Boolean designVerificationPlan;

    // Variáveis de PI & Jurídico
    private String statusPi;
    private Boolean freedomToOperate;
    private Boolean nonDisclosureAgreement;
    private Boolean materialTransferAgreement;
    private Boolean licença;

    // Variáveis de Testes & Qualidade
    private String tipoOuAmbienteTeste;
    private String resultados;
    private Boolean conformidades;

    // Variáveis de Mercado & Parcerias
    private Boolean proofOfConceptComCliente;
    private Boolean contratoLicençaFornecimento;
    private BigDecimal receitaInicial;

    // Variáveis de Regulatório
    private Boolean dossiesCertificacoes;

    //Cálculo TRL
    private Integer trl;
    private Float trlScore;


    public Long getIdAvaliacaoTrlPI() {
        return idAvaliacaoTrlPI;
    }

    public void setIdAvaliacaoTrlPI(Long idAvaliacaoTrlPI) {
        this.idAvaliacaoTrlPI = idAvaliacaoTrlPI;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca idMarca) {
        this.marca = idMarca;
    }

    public Patente getPatente() {
        return patente;
    }

    public void setPatente(Patente patente) {
        this.patente = patente;
    }

    public ContratoFranquia getContratoFranquia() {
        return contratoFranquia;
    }

    public void setContratoFranquia(ContratoFranquia contratoFranquia) {
        this.contratoFranquia = contratoFranquia;
    }

    public String getHipotese() {
        return hipotese;
    }

    public void setHipotese(String hipotese) {
        this.hipotese = hipotese;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    public String getRelatorio() {
        return relatorio;
    }

    public void setRelatorio(String relatorio) {
        this.relatorio = relatorio;
    }

    public String getEspecificacoes() {
        return especificacoes;
    }

    public void setEspecificacoes(String especificacoes) {
        this.especificacoes = especificacoes;
    }

    public Boolean getDesignVerificationTest() {
        return designVerificationTest;
    }

    public void setDesignVerificationTest(Boolean designVerificationTest) {
        this.designVerificationTest = designVerificationTest;
    }

    public Boolean getDesignVerificationPlan() {
        return designVerificationPlan;
    }

    public void setDesignVerificationPlan(Boolean designVerificationPlan) {
        this.designVerificationPlan = designVerificationPlan;
    }

    public String getStatusPi() {
        return statusPi;
    }

    public void setStatusPi(String statusPi) {
        this.statusPi = statusPi;
    }

    public Boolean getFreedomToOperate() {
        return freedomToOperate;
    }

    public void setFreedomToOperate(Boolean freedomToOperate) {
        this.freedomToOperate = freedomToOperate;
    }

    public Boolean getNonDisclosureAgreement() {
        return nonDisclosureAgreement;
    }

    public void setNonDisclosureAgreement(Boolean nonDisclosureAgreement) {
        this.nonDisclosureAgreement = nonDisclosureAgreement;
    }

    public Boolean getMaterialTransferAgreement() {
        return materialTransferAgreement;
    }

    public void setMaterialTransferAgreement(Boolean materialTransferAgreement) {
        this.materialTransferAgreement = materialTransferAgreement;
    }

    public Boolean getLicença() {
        return licença;
    }

    public void setLicença(Boolean licença) {
        this.licença = licença;
    }

    public String getTipoOuAmbienteTeste() {
        return tipoOuAmbienteTeste;
    }

    public void setTipoOuAmbienteTeste(String tipoOuAmbienteTeste) {
        this.tipoOuAmbienteTeste = tipoOuAmbienteTeste;
    }

    public String getResultados() {
        return resultados;
    }

    public void setResultados(String resultados) {
        this.resultados = resultados;
    }

    public Boolean getConformidades() {
        return conformidades;
    }

    public void setConformidades(Boolean conformidades) {
        this.conformidades = conformidades;
    }

    public Boolean getProofOfConceptComCliente() {
        return proofOfConceptComCliente;
    }

    public void setProofOfConceptComCliente(Boolean proofOfConceptComCliente) {
        this.proofOfConceptComCliente = proofOfConceptComCliente;
    }

    public Boolean getContratoLicençaFornecimento() {
        return contratoLicençaFornecimento;
    }

    public void setContratoLicençaFornecimento(Boolean contratoLicençaFornecimento) {
        this.contratoLicençaFornecimento = contratoLicençaFornecimento;
    }

    public BigDecimal getReceitaInicial() {
        return receitaInicial;
    }

    public void setReceitaInicial(BigDecimal receitaInicial) {
        this.receitaInicial = receitaInicial;
    }

    public Boolean getDossiesCertificacoes() {
        return dossiesCertificacoes;
    }

    public void setDossiesCertificacoes(Boolean dossiesCertificacoes) {
        this.dossiesCertificacoes = dossiesCertificacoes;
    }

    public Integer getTrl() {
        return trl;
    }

    public void setTrl(Integer trl) {
        this.trl = trl;
    }

    public Float getTrlScore() {
        return trlScore;
    }

    public void setTrlScore(Float trlScore) {
        this.trlScore = trlScore;
    }
}
