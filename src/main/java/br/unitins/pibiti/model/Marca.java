package br.unitins.pibiti.model;

import br.unitins.pibiti.enums.NaturezaMarca;
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
@Table(name = "marca")
@Getter
@Setter
public class Marca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_marca")
    private Long idMarca;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_propiedade_intelectual", nullable = false)
    private TipoPropiedadeIntelectual tipoPropiedadeIntelectual;

    @ManyToOne
    @JoinColumn(name = "id_nit")
    private Nit nit;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String titular;

    @Column(name = "data_concessao", nullable = false)
    private LocalDate dataConcessao;

    @Column(nullable = false)
    private String periodo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NaturezaMarca natureza;

    @Column(length = 800)
    private String classes;

    @Column(nullable = false)
    private Boolean visualizacaoPublica;

}
