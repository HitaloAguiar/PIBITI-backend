package br.unitins.pibiti.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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

    @OneToOne(mappedBy = "nit")
    private Responsavel responsavel;

    @OneToMany(mappedBy = "nit", cascade = CascadeType.ALL)
    private List<ServicoNit> servicos;

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

    public Responsavel getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Responsavel responsavel) {
        this.responsavel = responsavel;
    }

    public List<ServicoNit> getServicos() {
        return servicos;
    }

    public void setServicos(List<ServicoNit> servicos) {
        this.servicos = servicos;
    }
}
