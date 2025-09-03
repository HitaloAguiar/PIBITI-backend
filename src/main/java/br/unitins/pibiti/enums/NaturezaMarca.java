package br.unitins.pibiti.enums;

public enum NaturezaMarca {
    PRODUTO(1L, "Produto"),
    SERVICO(2L, "Serviço"),
    COLETIVA(3L, "Coletiva"),
    CERTIFICACAO(4L, "Certificação");

    private Long id;
    private String label;

    NaturezaMarca(Long id, String label) {
        this.id = id;
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public Long getId() {
        return id;
    }

    public static NaturezaMarca fromLabel(String label) {
        for (NaturezaMarca n : NaturezaMarca.values()) {
            if (n.getLabel().equalsIgnoreCase(label)) {
                return n;
            }
        }
        throw new IllegalArgumentException("Nenhuma NaturezaMarca encontrada para o label: " + label);
    }

    public static NaturezaMarca fromId(Long id) {
        for (NaturezaMarca n : NaturezaMarca.values()) {
            if (n.getId().equals(id)) {
                return n;
            }
        }
        throw new IllegalArgumentException("Nenhuma NaturezaMarca encontrada para o id: " + id);
    }
}
