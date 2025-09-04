package br.unitins.pibiti.model;

import br.unitins.pibiti.enums.TipoPatente;
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

import java.time.LocalDate;

@Entity
@Table(name = "patente")
@Getter
@Setter
public class Patente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_patente")
    private Long idPatente;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_propiedade_intelectual", nullable = false)
    private TipoPropiedadeIntelectual tipoPropiedadeIntelectual;

    @ManyToOne
    @JoinColumn(name = "id_nit")
    private Nit nit;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false, length = 1800)
    private String resumo;

    @Column(name = "data_concessao", nullable = false)
    private LocalDate dataConcessao;

    @Column(nullable = false)
    private String periodo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoPatente tipo;

    @Column(length = 800)
    private String classificacao;

    @Column(length = 800)
    private String categorias;

    @Column(nullable = false)
    private Boolean visualizacaoPublica;

}
