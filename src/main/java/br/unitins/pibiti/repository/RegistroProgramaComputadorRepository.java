package br.unitins.pibiti.repository;

import br.unitins.pibiti.model.Nit;
import br.unitins.pibiti.model.RegistroProgramaComputador;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RegistroProgramaComputadorRepository implements PanacheRepository<RegistroProgramaComputador> {

    public PanacheQuery<RegistroProgramaComputador> findListByNit(Nit nit, Sort sort) {

        if (nit == null)
            return null;

        return find("nit = ?1", sort, nit);
    }

    public PanacheQuery<RegistroProgramaComputador> findListByNitAndTitulo(Nit nit, String titulo, Sort sort) {

        if (nit == null || titulo == null)
            return null;

        return find("nit = ?1 AND UPPER(titulo) LIKE ?2", sort, nit, "%" + titulo.toUpperCase() + "%");
    }
}
