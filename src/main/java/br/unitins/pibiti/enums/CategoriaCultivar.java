package br.unitins.pibiti.enums;

public enum CategoriaCultivar {
    CULTIVAR_LTC(1L, "Cultivar local/tradicional/crioula"),
    CULTIVAR_EXPERIMENTAL_PRECOMERCIAL(2L, "Cultivar experimental ou pré-comercial"),
    HIBRIDO_PARENTAL(3L, "Híbrido parental"),
    CULTIVAR_EXPORTACAO(4L, "Cultivar para exportação"),
    CULTIVAR_COMERCIAL(5L, "Cultivar comercial"),
    OUTRO(6L, "Outro");

    private Long id;
    private String label;

    CategoriaCultivar(Long id, String label) {
        this.id = id;
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public Long getId() {
        return id;
    }

    public static CategoriaCultivar fromLabel(String label) {
        for (CategoriaCultivar n : CategoriaCultivar.values()) {
            if (n.getLabel().equalsIgnoreCase(label)) {
                return n;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para o label: " + label);
    }

    public static CategoriaCultivar fromId(Long id) {
        for (CategoriaCultivar n : CategoriaCultivar.values()) {
            if (n.getId().equals(id)) {
                return n;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada  para o id: " + id);
    }
}
