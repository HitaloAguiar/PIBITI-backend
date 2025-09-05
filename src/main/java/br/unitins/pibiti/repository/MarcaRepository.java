package br.unitins.pibiti.repository;

import br.unitins.pibiti.model.Marca;
import br.unitins.pibiti.model.Nit;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MarcaRepository implements PanacheRepository<Marca> {

    public PanacheQuery<Marca> findListByNit(Nit nit, Sort sort) {

        if (nit == null)
            return null;

        return find("nit = ?1", sort, nit);
    }

    public PanacheQuery<Marca> findListByNitAndNome(Nit nit, String nome, Sort sort) {

        if (nit == null || nome == null)
            return null;

        return find("nit = ?1 AND UPPER(nome) LIKE ?2", sort, nit, "%" + nome.toUpperCase() + "%");
    }

    public PanacheQuery<Marca> findAllPublico(Sort sort) {

        return find("visualizacaoPublica = ?1", sort, true);
    }

    public PanacheQuery<Marca> findAllPublicoFiltradoNome(Sort sort, String nome) {

        if (nome == null)
            return null;

        return find("visualizacaoPublica = ?1 AND UPPER(nome) LIKE ?2", sort, true, "%" + nome.toUpperCase() + "%");
    }
}
