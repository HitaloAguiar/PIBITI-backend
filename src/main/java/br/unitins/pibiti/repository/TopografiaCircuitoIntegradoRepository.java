package br.unitins.pibiti.repository;

import br.unitins.pibiti.model.Nit;
import br.unitins.pibiti.model.TopografiaCircuitoIntegrado;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TopografiaCircuitoIntegradoRepository implements PanacheRepository<TopografiaCircuitoIntegrado> {

    public PanacheQuery<TopografiaCircuitoIntegrado> findListByNit(Nit nit, Sort sort) {

        if (nit == null)
            return null;

        return find("nit = ?1", sort, nit);
    }

    public PanacheQuery<TopografiaCircuitoIntegrado> findListByNitAndTitulo(Nit nit, String titulo, Sort sort) {

        if (nit == null || titulo == null)
            return null;

        return find("nit = ?1 AND UPPER(titulo) LIKE ?2", sort, nit, "%" + titulo.toUpperCase() + "%");
    }

    public PanacheQuery<TopografiaCircuitoIntegrado> findAllPublico(Sort sort) {

        return find("visualizacaoPublica = ?1", sort, true);
    }

    public PanacheQuery<TopografiaCircuitoIntegrado> findAllPublicoFiltradoTitulo(Sort sort, String titulo) {

        if (titulo == null)
            return null;

        return find("visualizacaoPublica = ?1 AND UPPER(titulo) LIKE ?2", sort, true, "%" + titulo.toUpperCase() + "%");
    }
}
