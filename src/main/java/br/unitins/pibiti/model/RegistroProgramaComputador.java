package br.unitins.pibiti.model;

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
@Table(name = "registro_programa_computador")
@Getter
@Setter
public class RegistroProgramaComputador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_registro_programa_computador")
    private Long idRegistroProgramaComputador;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_propiedade_intelectual", nullable = false)
    private TipoPropiedadeIntelectual tipoPropiedadeIntelectual;

    @ManyToOne
    @JoinColumn(name = "id_nit")
    private Nit nit;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String descricao;

    @ElementCollection
    @CollectionTable(name = "registro_programa_autor",
            joinColumns = @JoinColumn(name = "id_registro_programa_autor"))
    @Column(name = "autor", nullable = false)
    private List<String> autores;

    @ElementCollection
    @CollectionTable(name = "registros_programa_linguagem",
            joinColumns = @JoinColumn(name = "id_linguagem"))
    @Column(name = "linguagem", nullable = false)
    private List<String> linguagens;

    private String campoAplicacao;

    private String tipoPrograma;

    @Column(nullable = false)
    private Boolean visualizacaoPublica;
}
