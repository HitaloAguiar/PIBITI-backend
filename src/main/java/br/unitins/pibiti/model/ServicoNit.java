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
@Table(name = "servico_fornecido_nit")
public class ServicoNit extends DefaultEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_servico_nit")
    private Long idServicoNit;

    @ManyToOne
    @JoinColumn(name = "id_servico_fornecido")
    private ServicoFornecido servico;

    @ManyToOne
    @JoinColumn(name = "id_nit")
    private Nit nit;

    public Long getIdServicoNit() {
        return idServicoNit;
    }

    public ServicoFornecido getServico() {
        return servico;
    }

    public void setServico(ServicoFornecido servico) {
        this.servico = servico;
    }

    public Nit getNit() {
        return nit;
    }

    public void setNit(Nit nit) {
        this.nit = nit;
    }
}
