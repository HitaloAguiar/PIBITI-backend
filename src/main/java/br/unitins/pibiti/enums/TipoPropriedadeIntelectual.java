package br.unitins.pibiti.enums;

public enum TipoPropriedadeIntelectual {
    PRIPRIEDADE_INDUSTRIAL(1L, "Propriedade Industrial"),
    DIREITO_AUTORAL(2L, "Direito Autoral"),
    PROTECAO_SUI_GENERIS(3L, "Proteção Sui Generis");

    private Long id;
    private String label;

    TipoPropriedadeIntelectual(Long id, String label) {
        this.id = id;
        this.label = label;
    }
    public String getLabel() {
        return label;
    }

    public Long getId() {
        return id;
    }

}
