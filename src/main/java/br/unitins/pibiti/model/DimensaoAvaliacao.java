package br.unitins.pibiti.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "dimensao_avaliacao_nit")
public class DimensaoAvaliacao extends DefaultEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dimensao_avaliacao_nit")
    private Long idDimensaoAvaliacaoNit;

    private Double imd;

    @ManyToOne
    @JoinColumn(name = "id_dimensao")
    private Dimensao dimensao;

    @ManyToOne
    @JoinColumn(name = "id_avaliacao_maturidade_nit")
    private AvaliacaoMaturidade avaliacao;

    public Long getIdDimensaoAvaliacaoNit() {
        return idDimensaoAvaliacaoNit;
    }

    public Double getImd() {
        return imd;
    }

    public void setImd(Double imd) {
        this.imd = imd;
    }

    public Dimensao getDimensao() {
        return dimensao;
    }

    public void setDimensao(Dimensao dimensao) {
        this.dimensao = dimensao;
    }

    public AvaliacaoMaturidade getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(AvaliacaoMaturidade avaliacao) {
        this.avaliacao = avaliacao;
    }
}
