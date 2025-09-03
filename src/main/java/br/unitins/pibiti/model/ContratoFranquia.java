package br.unitins.pibiti.model;

import br.unitins.pibiti.enums.TipoPropiedadeIntelectual;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "contrato_franquia")
public class ContratoFranquia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contrato_franquia")
    private Long idContratoFranquia;

    private TipoPropiedadeIntelectual tipoPropiedadeIntelectual;

    @ManyToOne
    @JoinColumn(name = "id_nit")
    private Nit nit;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String descricao;

    public Long getIdContratoFranquia() {
        return idContratoFranquia;
    }

    public void setIdContratoFranquia(Long idContratoFranquia) {
        this.idContratoFranquia = idContratoFranquia;
    }

    public TipoPropiedadeIntelectual getTipoPropiedadeIntelectual() {
        return tipoPropiedadeIntelectual;
    }

    public void setTipoPropiedadeIntelectual(TipoPropiedadeIntelectual tipoPropiedadeIntelectual) {
        this.tipoPropiedadeIntelectual = tipoPropiedadeIntelectual;
    }

    public Nit getNit() {
        return nit;
    }

    public void setNit(Nit nit) {
        this.nit = nit;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
