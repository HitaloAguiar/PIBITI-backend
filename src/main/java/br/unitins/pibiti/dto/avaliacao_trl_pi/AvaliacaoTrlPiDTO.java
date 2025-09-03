package br.unitins.pibiti.dto.avaliacao_trl_pi;

import java.math.BigDecimal;

public record AvaliacaoTrlPiDTO(
        // Propiedade Intelectual da Avaliação
        Long idMarca, Long idPatente, Long idContratoFranquia, Long idDesenhoIndustrial, Long idIndicacaoGeografica,
        Long idDireitoAutor, Long idRegistroProgramaComputador, Long idCultivar, Long idTopografiaCircuitoIntegrado,

        // Variáveis Científicas/Técnicas
        String hipotese, String protocolo, String relatorio, String especificacoes, Boolean designVerificationTest,
        Boolean designVerificationPlan,

        // Variáveis de PI & Jurídico
        String statusPi, Boolean freedomToOperate, Boolean nonDisclosureAgreement, Boolean materialTransferAgreement,
        Boolean licença,

        // Variáveis de Testes & Qualidade
        String tipoOuAmbienteTeste, String resultados, Boolean conformidades,

        // Variáveis de Mercado & Parcerias
        Boolean proofOfConceptComCliente, Boolean contratoLicencaFornecimento, BigDecimal receitaInicial,

        // Variáveis de Regulatório
        Boolean dossiesCertificacoes

) {
}
