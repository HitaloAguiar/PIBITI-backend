package br.unitins.pibiti.enums;

public enum Genero {
    ANTOLOGIA(1L, "Antologia"),
    ARGUMENTO(2L, "Argumento"),
    ARTIGO(3L, "Artigo"),
    AUTOBIOGRAFIA(4L, "Autobiografia"),
    CARTAZ_FOLDER_PLANFLETO(5L, "Cartaz/folder/panfleto"),
    COMICS(6L, "Comics"),
    CONFERENCI(7L, "Conferência"),
    CONTO(8L, "Conto"),
    CRONICA(9L, "Crônica"),
    DESENHO(10L, "Desenho"),
    DESIGN_WEBSITE(11L, "Design de Website"),
    DICIONARIO(12L, "Dicionário"),
    DIDATICO(13L, "Didático"),
    ENSAIO(14L, "Ensaio"),
    FOTOGRAFICA(15L, "Fotografia"),
    GUIA(16L, "Guia"),
    HISTORIA_QUADRINHOS(17L, "História em Quadrinhos"),
    LITERATURA_INFANTIL(18L, "Literatura Infantil"),
    LETRA_MUSICA(19L, "Letra de Música"),
    LIVRO_JOGO(20L, "Livro-jogo (RPG)"),
    MAPA(21L, "Mapa"),
    MISTICO_ESOTERICO(22L, "Místico/esotérico"),
    MONOGRAFIA(23L, "Monografia"),
    MUSICA(23L, "Música"),
    NOVELA(24L, "Novela"),
    PERIODICO(25L, "Periódico (jornal, revista)"),
    PERSONAGEM(26L, "Personagem"),
    POEMA(27L, "Poema"),
    RELIGIOSO(28L, "Religioso"),
    ROMANCE(29L, "Romance"),
    ROTEIRO(30L, "Roteiro (audiovisual)"),
    TEATRO(31L, "Teatro"),
    TECNICO(32L, "Técnico"),
    TESE(34L, "Tese"),
    OUTROS(35L, "Outros");

    private Long id;
    private String label;

    Genero(Long id, String label) {
        this.id = id;
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public Long getId() {
        return id;
    }

    public static Genero fromLabel(String label) {
        for (Genero n : Genero.values()) {
            if (n.getLabel().equalsIgnoreCase(label)) {
                return n;
            }
        }
        throw new IllegalArgumentException("Nenhum Gênero encontrado para o label: " + label);
    }

    public static Genero fromId(Long id) {
        for (Genero n : Genero.values()) {
            if (n.getId().equals(id)) {
                return n;
            }
        }
        throw new IllegalArgumentException("Nenhum Gênero encontrado para o id: " + id);
    }
}
