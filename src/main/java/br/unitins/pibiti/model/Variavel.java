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
@Table(name = "variavel_maturidade_nit")
public class Variavel extends DefaultEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_variavel_maturidade_nit")
    private Long idVariavelMaturidadeNIT;

    private String nome;

    private Double peso;

    @ManyToOne
    @JoinColumn(name = "id_dimensao")
    private Dimensao dimensao;

    public Long getIdVariavelMaturidadeNIT() {
        return idVariavelMaturidadeNIT;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Dimensao getDimensao() {
        return dimensao;
    }

    public void setDimensao(Dimensao dimensao) {
        this.dimensao = dimensao;
    }
}
