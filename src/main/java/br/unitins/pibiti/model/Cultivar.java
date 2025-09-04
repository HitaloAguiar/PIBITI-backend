package br.unitins.pibiti.model;

import br.unitins.pibiti.enums.CategoriaCultivar;
import br.unitins.pibiti.enums.TipoPropiedadeIntelectual;
import jakarta.persistence.Column;
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

@Entity
@Table(name = "cultivar")
@Getter
@Setter
public class Cultivar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cultivar")
    private Long idCultivar;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_propiedade_intelectual", nullable = false)
    private TipoPropiedadeIntelectual tipoPropiedadeIntelectual;

    @ManyToOne
    @JoinColumn(name = "id_nit")
    private Nit nit;

    @Column(nullable = false, length = 1000)
    private String titulo;

    @Column(nullable = false, length = 1800)
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoriaCultivar categoria;

    @Column(nullable = false)
    private Boolean visualizacaoPublica;

}
