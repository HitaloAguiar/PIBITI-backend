package br.unitins.pibiti.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class VariavelAvaliacao extends DefaultEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_variavel_avaliacao_nit")
    private Long idVariavelAvaliacao;

    private Boolean selecionado;

    @ManyToOne
    @JoinColumn(name = "id_variavel_nit")
    private Variavel variavel;

    @ManyToOne
    @JoinColumn(name = "id_avaliacao_maturidade_nit")
    private AvaliacaoMaturidade avaliacao;

    public Long getIdVariavelAvaliacao() {
        return idVariavelAvaliacao;
    }

    public Boolean getSelecionado() {
        return selecionado;
    }

    public void setSelecionado(Boolean selecionado) {
        this.selecionado = selecionado;
    }

    public Variavel getVariavel() {
        return variavel;
    }

    public void setVariavel(Variavel variavel) {
        this.variavel = variavel;
    }

    public AvaliacaoMaturidade getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(AvaliacaoMaturidade avaliacao) {
        this.avaliacao = avaliacao;
    }
}
