package br.unitins.pibiti.model;

import br.unitins.pibiti.dto.responsavel.ResponsavelDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Responsavel extends DefaultEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_responsavel")
    private Long idResponsavel;

    @Column(name = "nome_completo")
    private String nomeCompleto;

    private String cpf;

    private String email;

    private String telefone;

    private String cargo;

    @OneToOne
    @JoinColumn(name = "id_nit", unique = true)
    private Nit nit;

    public Responsavel() {
    }

    public Responsavel(ResponsavelDTO responsavelDTO) {

        this.nomeCompleto = responsavelDTO.nomeCompleto();
        this.cpf = responsavelDTO.cpf();
        this.email = responsavelDTO.email();
        this.telefone = responsavelDTO.telefone();
        this.cargo = responsavelDTO.cargo();
    }

    public Long getIdResponsavel() {
        return idResponsavel;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Nit getNit() {
        return nit;
    }

    public void setNit(Nit nit) {
        this.nit = nit;
    }
}
