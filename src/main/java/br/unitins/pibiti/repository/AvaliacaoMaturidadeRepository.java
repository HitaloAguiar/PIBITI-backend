package br.unitins.pibiti.repository;

import br.unitins.pibiti.model.AvaliacaoMaturidade;
import br.unitins.pibiti.model.Nit;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AvaliacaoMaturidadeRepository implements PanacheRepository<AvaliacaoMaturidade> {

    public AvaliacaoMaturidade findByNit(Nit nit) {

        if (nit == null)
            return null;

        return find("nit = ?1", nit).firstResult();
    }

    public AvaliacaoMaturidade findByNitAndLastInserted(Nit nit) {
        if (nit == null)
            return null;

        return find("nit = ?1 ORDER BY createdAt DESC", nit).firstResult();
    }

    public PanacheQuery<AvaliacaoMaturidade> findListByNit(Nit nit) {

        if (nit == null)
            return null;

        return find("nit = ?1", nit);
    }

    public AvaliacaoMaturidade findById(String id) {
        if (id == null)
            return null;

        return find("idAvaliacaoMaturidade = ?1", id).firstResult();
    }
}
