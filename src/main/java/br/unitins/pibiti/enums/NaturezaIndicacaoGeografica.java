package br.unitins.pibiti.enums;

public enum NaturezaIndicacaoGeografica {
    PRODUTO(1L, "Produto"),
    SERVICO(2L, "Serviço");

    private Long id;
    private String label;

    NaturezaIndicacaoGeografica(Long id, String label) {
        this.id = id;
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public Long getId() {
        return id;
    }

    public static NaturezaIndicacaoGeografica fromLabel(String label) {
        for (NaturezaIndicacaoGeografica n : NaturezaIndicacaoGeografica.values()) {
            if (n.getLabel().equalsIgnoreCase(label)) {
                return n;
            }
        }
        throw new IllegalArgumentException("Nenhuma Natureza de Indicação Geográfica encontrada para o label: " + label);
    }

    public static NaturezaIndicacaoGeografica fromId(Long id) {
        for (NaturezaIndicacaoGeografica n : NaturezaIndicacaoGeografica.values()) {
            if (n.getId().equals(id)) {
                return n;
            }
        }
        throw new IllegalArgumentException("Nenhuma Natureza de Indicação Geográfica encontrada para o id: " + id);
    }
}
