package br.unitins.pibiti.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "avaliacao_trl_propiedade_intelectual")
@Getter
@Setter
public class AvaliacaoTRLPropiedadeIntelectual extends DefaultEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_avaliacao_trl_propiedade_intelectual")
    private Long idAvaliacaoTrlPI;

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

    @ManyToOne
    @JoinColumn(name = "id_desenho_industrial")
    private DesenhoIndustrial desenhoIndustrial;

    @ManyToOne
    @JoinColumn(name = "id_indicacao_geografica")
    private IndicacaoGeografica indicacaoGeografica;

    @ManyToOne
    @JoinColumn(name = "id_direito_autor")
    private DireitoAutor direitoAutor;

    @ManyToOne
    @JoinColumn(name = "id_registro_programa_computador")
    private RegistroProgramaComputador registroProgramaComputador;
}
