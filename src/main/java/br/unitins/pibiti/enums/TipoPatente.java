package br.unitins.pibiti.enums;

public enum TipoPatente {
    MODELO_UTILIDADE(1L, "Modelo de Utilidade"),
    PATENTE_INVENCAO(2L, "Patente de Invenção");

    private Long id;
    private String label;

    TipoPatente(Long id, String label) {
        this.id = id;
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public Long getId() {
        return id;
    }

    public static TipoPatente fromLabel(String label) {
        for (TipoPatente n : TipoPatente.values()) {
            if (n.getLabel().equalsIgnoreCase(label)) {
                return n;
            }
        }
        throw new IllegalArgumentException("Nenhum TipoPatente encontrad para o label: " + label);
    }

    public static TipoPatente fromId(Long id) {
        for (TipoPatente n : TipoPatente.values()) {
            if (n.getId().equals(id)) {
                return n;
            }
        }
        throw new IllegalArgumentException("Nenhum TipoPatente encontrad para o id: " + id);
    }
}
