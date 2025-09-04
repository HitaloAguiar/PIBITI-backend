package br.unitins.pibiti.model;

import br.unitins.pibiti.enums.Genero;
import br.unitins.pibiti.enums.TipoPropiedadeIntelectual;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "direito_autor")
@Getter
@Setter
public class DireitoAutor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_direito_autor")
    private Long idDireitoAutor;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_propiedade_intelectual", nullable = false)
    private TipoPropiedadeIntelectual tipoPropiedadeIntelectual;

    @ManyToOne
    @JoinColumn(name = "id_nit")
    private Nit nit;

    @Column(nullable = false, length = 1000)
    private String titulo;

    @ElementCollection
    @CollectionTable(name = "direito_autor_autores",
            joinColumns = @JoinColumn(name = "id_direito_autor"))
    @Column(name = "autor", nullable = false)
    private List<String> autores;

    @Column(nullable = false, length = 1800)
    private String descricao;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "direito_autor_generos",
            joinColumns = @JoinColumn(name = "id_direito_autor"))
    @Column(name = "genero", nullable = false)
    private List<Genero> generos;

    private Integer numeroTotalPaginasObra;

    private Boolean adapatacaoOuTraducao;

    @Column(length = 1000)
    private String tituloObraOriginal;

    private String autoresObraOriginal;

    @Column(nullable = false)
    private Boolean visualizacaoPublica;

}
