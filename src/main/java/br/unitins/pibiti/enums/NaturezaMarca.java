package br.unitins.pibiti.enums;

public enum NaturezaMarca {
    PRODUTO("Produto"),
    SERVICO("Serviço"),
    COLETIVA("Coletiva"),
    CERTIFICACAO("Certificação");

    private String label;

    NaturezaMarca(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
