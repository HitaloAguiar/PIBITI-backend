package br.unitins.pibiti.model;

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
    private Long idAvaliacaoMaturidade;

    private Double imc;

    @ManyToOne
    @JoinColumn(name = "id_nit")
    private Nit nit;

    public Long getIdAvaliacaoMaturidade() {
        return idAvaliacaoMaturidade;
    }

    public Double getImc() {
        return imc;
    }

    public void setImc(Double imc) {
        this.imc = imc;
    }

    public Nit getNit() {
        return nit;
    }

    public void setNit(Nit nit) {
        this.nit = nit;
    }
}
