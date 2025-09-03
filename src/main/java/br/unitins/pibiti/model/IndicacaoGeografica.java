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

import java.time.LocalDate;

@Entity
@Table(name = "indicacao_geografica")
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

    public Long getIdIndicacaoGeografica() {
        return idIndicacaoGeografica;
    }

    public void setIdIndicacaoGeografica(Long idIndicacaoGeografica) {
        this.idIndicacaoGeografica = idIndicacaoGeografica;
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

    public LocalDate getDataConcessao() {
        return dataConcessao;
    }

    public void setDataConcessao(LocalDate dataConcessao) {
        this.dataConcessao = dataConcessao;
    }

    public EspecieIndicacaoGeografica getEspecie() {
        return especie;
    }

    public void setEspecie(EspecieIndicacaoGeografica especie) {
        this.especie = especie;
    }

    public NaturezaIndicacaoGeografica getNatureza() {
        return natureza;
    }

    public void setNatureza(NaturezaIndicacaoGeografica natureza) {
        this.natureza = natureza;
    }

    public String getTituloProdutoServico() {
        return tituloProdutoServico;
    }

    public void setTituloProdutoServico(String tituloProdutoServico) {
        this.tituloProdutoServico = tituloProdutoServico;
    }

    public String getDelimitacao() {
        return delimitacao;
    }

    public void setDelimitacao(String delimitacao) {
        this.delimitacao = delimitacao;
    }

    public String getRequerente() {
        return requerente;
    }

    public void setRequerente(String requerente) {
        this.requerente = requerente;
    }
}
