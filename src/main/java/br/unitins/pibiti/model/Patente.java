package br.unitins.pibiti.model;

import br.unitins.pibiti.enums.TipoPatente;
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
@Table(name = "patente")
public class Patente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_patente")
    private Long idPatente;

    private TipoPropiedadeIntelectual tipoPropiedadeIntelectual;

    @ManyToOne
    @JoinColumn(name = "id_nit")
    private Nit nit;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String resumo;

    @Column(name = "data_concessao", nullable = false)
    private LocalDate dataConcessao;

    @Column(nullable = false)
    private String periodo;

    @Column(nullable = false)
    private TipoPatente tipo;

    private String classificacao;

    private String categorias;

    public Long getIdPatente() {
        return idPatente;
    }

    public void setIdPatente(Long idPatente) {
        this.idPatente = idPatente;
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

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
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

    public TipoPatente getTipo() {
        return tipo;
    }

    public void setTipo(TipoPatente tipo) {
        this.tipo = tipo;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    public String getCategorias() {
        return categorias;
    }

    public void setCategorias(String categorias) {
        this.categorias = categorias;
    }
}
