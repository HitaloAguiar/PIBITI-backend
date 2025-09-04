package br.unitins.pibiti.model;

import br.unitins.pibiti.enums.TipoPropriedadeIntelectual;
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
@Table(name = "topografia_circuito_integrado")
@Getter
@Setter
public class TopografiaCircuitoIntegrado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_topografia_circuito_integrado")
    private Long idTopografiaCircuitoIntegrado;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_propriedade_intelectual", nullable = false)
    private TipoPropriedadeIntelectual tipoPropriedadeIntelectual;

    @ManyToOne
    @JoinColumn(name = "id_nit")
    private Nit nit;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false, length = 1800)
    private String descricao;

    @ElementCollection
    @CollectionTable(name = "topografia_circuitos_integrados_autor",
            joinColumns = @JoinColumn(name = "id_topografia_circuito_integrado"))
    @Column(name = "autor", nullable = false)
    private List<String> autores;

    @Column(nullable = false)
    private Boolean visualizacaoPublica;

}
