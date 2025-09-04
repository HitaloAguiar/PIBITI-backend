package br.unitins.pibiti.model;

import br.unitins.pibiti.enums.EspecieIndicacaoGeografica;
import br.unitins.pibiti.enums.NaturezaIndicacaoGeografica;
import br.unitins.pibiti.enums.TipoPropiedadeIntelectual;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "indicacao_geografica")
@Getter
@Setter
public class IndicacaoGeografica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_indicacao_geografica")
    private Long idIndicacaoGeografica;

    private TipoPropiedadeIntelectual tipoPropiedadeIntelectual;

    @ManyToOne
    @JoinColumn(name = "id_nit")
    private Nit nit;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String descricao;

    @Column(name = "data_concessao", nullable = false)
    private LocalDate dataConcessao;

    @Column(nullable = false)
    private EspecieIndicacaoGeografica especie;

    @Column(nullable = false)
    private NaturezaIndicacaoGeografica natureza;

    @Column(nullable = false)
    private String tituloProdutoServico;

    @Column(nullable = false)
    private String delimitacao;

    private String requerente;

    @Column(nullable = false)
    private Boolean visualizacaoPublica;
}
