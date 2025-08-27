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
@Table(name = "avaliacao_maturidade_nit")
public class AvaliacaoMaturidade extends DefaultEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_avaliacao_maturidade_nit")
    private Long idAvaliacaoMaturidade;

    @Column(name = "nivel_maturidade")
    private String nivelMaturidade;

    private Double img;

    @ManyToOne
    @JoinColumn(name = "id_nit")
    private Nit nit;

    public Long getIdAvaliacaoMaturidade() {
        return idAvaliacaoMaturidade;
    }

    public Double getImg() {
        return img;
    }

    public void setImg(Double img) {
        this.img = img;
    }

    public Nit getNit() {
        return nit;
    }

    public void setNit(Nit nit) {
        this.nit = nit;
    }

    public String getNivelMaturidade() {
        return nivelMaturidade;
    }

    public void setNivelMaturidade(String nivelMaturidade) {
        this.nivelMaturidade = nivelMaturidade;
    }
}
