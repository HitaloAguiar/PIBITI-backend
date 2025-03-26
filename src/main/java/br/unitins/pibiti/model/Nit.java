package br.unitins.pibiti.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Nit extends DefaultEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_nit")
    private Long idNit;

    @Column(unique = true)
    private String cnpj;

    private String email;

    private String telefone;

    @Column(name = "ano_inicio_atividades")
    private LocalDate anoInicioAtividades;

    private String ict;

    private Boolean privacidade;

    private String senha;

    @Column(name = "foto_perfil")
    private String fotoPerfil;

    @OneToOne
    @JoinColumn(name = "id_responsavel", unique = true)
    private Responsavel responsavel;

    @ManyToMany
    @JoinTable(name = "servico_nit",
                joinColumns = @JoinColumn(name = "id_nit"),
                inverseJoinColumns = @JoinColumn(name = "id_servico"))
    private List<ServicoFornecido> servicos;

    public Long getIdNit() {
        return idNit;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
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

    public LocalDate getAnoInicioAtividades() {
        return anoInicioAtividades;
    }

    public void setAnoInicioAtividades(LocalDate anoInicioAtividades) {
        this.anoInicioAtividades = anoInicioAtividades;
    }

    public String getIct() {
        return ict;
    }

    public void setIct(String ict) {
        this.ict = ict;
    }

    public Boolean getPrivacidade() {
        return privacidade;
    }

    public void setPrivacidade(Boolean privacidade) {
        this.privacidade = privacidade;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }
}
