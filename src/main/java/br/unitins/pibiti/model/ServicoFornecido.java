package br.unitins.pibiti.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "servico_fornecido")
public class ServicoFornecido extends DefaultEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idServicoFornecido;

    private String nome;

    @OneToMany(mappedBy = "servico", cascade = CascadeType.ALL)
    private List<ServicoNit> nits;

    public Long getIdServicoFornecido() {
        return idServicoFornecido;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
