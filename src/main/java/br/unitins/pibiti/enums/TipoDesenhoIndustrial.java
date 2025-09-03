package br.unitins.pibiti.enums;

public enum TipoDesenhoIndustrial {
    BIDIMENSIONAL(1L, "Bidimensional"),
    TRIDIMENSIONAL(2L, "Tridimensional");

    private Long id;
    private String label;

    TipoDesenhoIndustrial(Long id, String label) {
        this.id = id;
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public Long getId() {
        return id;
    }

    public static TipoDesenhoIndustrial fromLabel(String label) {
        for (TipoDesenhoIndustrial n : TipoDesenhoIndustrial.values()) {
            if (n.getLabel().equalsIgnoreCase(label)) {
                return n;
            }
        }
        throw new IllegalArgumentException("Nenhum TipoDesenhoIndustrial encontrado para o label: " + label);
    }

    public static TipoDesenhoIndustrial fromId(Long id) {
        for (TipoDesenhoIndustrial n : TipoDesenhoIndustrial.values()) {
            if (n.getId().equals(id)) {
                return n;
            }
        }
        throw new IllegalArgumentException("Nenhum TipoDesenhoIndustrial encontrado para o id: " + id);
    }
}
