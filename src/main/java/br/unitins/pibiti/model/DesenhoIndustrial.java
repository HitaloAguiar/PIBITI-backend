package br.unitins.pibiti.model;

import br.unitins.pibiti.enums.TipoDesenhoIndustrial;
import br.unitins.pibiti.enums.TipoPropiedadeIntelectual;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "desenho_industrial")
public class DesenhoIndustrial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_desenho_industrial")
    private Long idDesenhoIndustriaç;

    private TipoPropiedadeIntelectual tipoPropiedadeIntelectual;

    @ManyToOne
    @JoinColumn(name = "id_nit")
    private Nit nit;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String titular;

    @Column(nullable = false)
    private String descricao;

    @Column(name = "data_concessao", nullable = false)
    private LocalDate dataConcessao;

    @Column(nullable = false)
    private String periodo;

    private TipoDesenhoIndustrial tipoDesenhoIndustrial;

    public Long getIdDesenhoIndustriaç() {
        return idDesenhoIndustriaç;
    }

    public void setIdDesenhoIndustriaç(Long idDesenhoIndustriaç) {
        this.idDesenhoIndustriaç = idDesenhoIndustriaç;
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

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataConcessao() {
        return dataConcessao;
    }

    public void setDataConcessao(LocalDate dataConcessao) {
        this.dataConcessao = dataConcessao;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public TipoDesenhoIndustrial getTipoDesenhoIndustrial() {
        return tipoDesenhoIndustrial;
    }

    public void setTipoDesenhoIndustrial(TipoDesenhoIndustrial tipoDesenhoIndustrial) {
        this.tipoDesenhoIndustrial = tipoDesenhoIndustrial;
    }
}
