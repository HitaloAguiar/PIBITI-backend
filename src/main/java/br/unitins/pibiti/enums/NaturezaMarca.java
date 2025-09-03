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

    public static NaturezaMarca fromLabel(String label) {
        for (NaturezaMarca n : NaturezaMarca.values()) {
            if (n.getLabel().equalsIgnoreCase(label)) {
                return n;
            }
        }
        throw new IllegalArgumentException("Nenhuma NaturezaMarca encontrada para o label: " + label);
    }
}
