package br.unitins.pibiti.model;

import br.unitins.pibiti.enums.NaturezaMarca;
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
@Table(name = "marca")
public class Marca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_marca")
    private Long idVariavelAvaliacao;

    @ManyToOne
    @JoinColumn(name = "id_tipo_propiedade_intelectual")
    private TipoPropiedadeIntelectual tipoPropiedadeIntelectual;

    private String nome;
    private String titular;
    private LocalDate dataConcessao;
    private String periodo;

    private NaturezaMarca natureza;
    private String classes;

    public Long getIdVariavelAvaliacao() {
        return idVariavelAvaliacao;
    }

    public void setIdVariavelAvaliacao(Long idVariavelAvaliacao) {
        this.idVariavelAvaliacao = idVariavelAvaliacao;
    }

    public TipoPropiedadeIntelectual getTipoPropiedadeIntelectual() {
        return tipoPropiedadeIntelectual;
    }

    public void setTipoPropiedadeIntelectual(TipoPropiedadeIntelectual tipoPropiedadeIntelectual) {
        this.tipoPropiedadeIntelectual = tipoPropiedadeIntelectual;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
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

    public NaturezaMarca getNatureza() {
        return natureza;
    }

    public void setNatureza(NaturezaMarca natureza) {
        this.natureza = natureza;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

}
