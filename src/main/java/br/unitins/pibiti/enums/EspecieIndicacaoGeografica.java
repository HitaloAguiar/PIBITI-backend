package br.unitins.pibiti.enums;

public enum EspecieIndicacaoGeografica {
    INDICACAO_PROCEDENCIA(1L, "Indicação de Procedência"),
    DENOMINACAO_ORIGEM(2L, "Denominação de Origem");

    private Long id;
    private String label;

    EspecieIndicacaoGeografica(Long id, String label) {
        this.id = id;
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public Long getId() {
        return id;
    }

    public static EspecieIndicacaoGeografica fromLabel(String label) {
        for (EspecieIndicacaoGeografica n : EspecieIndicacaoGeografica.values()) {
            if (n.getLabel().equalsIgnoreCase(label)) {
                return n;
            }
        }
        throw new IllegalArgumentException("Nenhuma Espécie encontrada para o label: " + label);
    }

    public static EspecieIndicacaoGeografica fromId(Long id) {
        for (EspecieIndicacaoGeografica n : EspecieIndicacaoGeografica.values()) {
            if (n.getId().equals(id)) {
                return n;
            }
        }
        throw new IllegalArgumentException("Nenhuma Espécie encontrada para o id: " + id);
    }
}
