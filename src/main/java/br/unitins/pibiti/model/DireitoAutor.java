package br.unitins.pibiti.model;

import br.unitins.pibiti.enums.Genero;
import br.unitins.pibiti.enums.TipoPropiedadeIntelectual;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "direito_autor")
public class DireitoAutor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_direito_autor")
    private Long idDireitoAutor;

    private TipoPropiedadeIntelectual tipoPropiedadeIntelectual;

    @ManyToOne
    @JoinColumn(name = "id_nit")
    private Nit nit;

    @Column(nullable = false)
    private String titulo;

    @ElementCollection
    @CollectionTable(name = "direito_autor_autores",
            joinColumns = @JoinColumn(name = "id_direito_autor"))
    @Column(name = "autor", nullable = false)
    private List<String> autores;

    @Column(nullable = false)
    private String descricao;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "direito_autor_generos",
            joinColumns = @JoinColumn(name = "id_direito_autor"))
    @Column(name = "genero", nullable = false)
    private List<Genero> generos;

    private Integer numeroTotalPaginasObra;

    private Boolean adapatacaoOuTraducao;

    private String tituloObraOriginal;
    private String autoresObraOriginal;

    public Long getIdDireitoAutor() {
        return idDireitoAutor;
    }

    public void setIdDireitoAutor(Long idDireitoAutor) {
        this.idDireitoAutor = idDireitoAutor;
    }

    public TipoPropiedadeIntelectual getTipoPropiedadeIntelectual() {
        return tipoPropiedadeIntelectual;
    }

    public void setTipoPropiedadeIntelectual(TipoPropiedadeIntelectual tipoPropiedadeIntelectual) {
        this.tipoPropiedadeIntelectual = tipoPropiedadeIntelectual;
    }

    public Nit getNit() {
        return nit;
    }

    public void setNit(Nit nit) {
        this.nit = nit;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<String> getAutores() {
        return autores;
    }

    public void setAutores(List<String> autores) {
        this.autores = autores;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Genero> getGeneros() {
        return generos;
    }

    public void setGeneros(List<Genero> generos) {
        this.generos = generos;
    }

    public Integer getNumeroTotalPaginasObra() {
        return numeroTotalPaginasObra;
    }

    public void setNumeroTotalPaginasObra(Integer numeroTotalPaginasObra) {
        this.numeroTotalPaginasObra = numeroTotalPaginasObra;
    }

    public Boolean getAdapatacaoOuTraducao() {
        return adapatacaoOuTraducao;
    }

    public void setAdapatacaoOuTraducao(Boolean adapatacaoOuTraducao) {
        this.adapatacaoOuTraducao = adapatacaoOuTraducao;
    }

    public String getTituloObraOriginal() {
        return tituloObraOriginal;
    }

    public void setTituloObraOriginal(String tituloObraOriginal) {
        this.tituloObraOriginal = tituloObraOriginal;
    }

    public String getAutoresObraOriginal() {
        return autoresObraOriginal;
    }

    public void setAutoresObraOriginal(String autoresObraOriginal) {
        this.autoresObraOriginal = autoresObraOriginal;
    }
}
