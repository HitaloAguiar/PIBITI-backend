package br.unitins.pibiti.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tipo_propiedade_intelectual")
public class TipoPropiedadeIntelectual {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_propiedade_intelectual")
    private Long idTipoPropiedadeIntelectual;

    @Column(nullable = false)
    private String nome;

    public Long getIdTipoPropiedadeIntelectual() {
        return idTipoPropiedadeIntelectual;
    }

    public void setIdTipoPropiedadeIntelectual(Long idTipoPropiedadeIntelectual) {
        this.idTipoPropiedadeIntelectual = idTipoPropiedadeIntelectual;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
