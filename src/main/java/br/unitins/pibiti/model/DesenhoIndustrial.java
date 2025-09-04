package br.unitins.pibiti.model;

import br.unitins.pibiti.enums.TipoDesenhoIndustrial;
import br.unitins.pibiti.enums.TipoPropriedadeIntelectual;
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
@Table(name = "desenho_industrial")
@Getter
@Setter
public class DesenhoIndustrial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_desenho_industrial")
    private Long idDesenhoIndustrial;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_propriedade_intelectual", nullable = false)
    private TipoPropriedadeIntelectual tipoPropriedadeIntelectual;

    @ManyToOne
    @JoinColumn(name = "id_nit")
    private Nit nit;

    @Column(nullable = false, length = 1000)
    private String titulo;

    @Column(nullable = false)
    private String titular;

    @Column(nullable = false, length = 1800)
    private String descricao;

    @Column(name = "data_concessao", nullable = false)
    private LocalDate dataConcessao;

    @Column(nullable = false)
    private String periodo;

    @Enumerated(EnumType.STRING)
    private TipoDesenhoIndustrial tipoDesenhoIndustrial;

    @Column(nullable = false)
    private Boolean visualizacaoPublica;
}
