package br.unitins.pibiti.repository;

import br.unitins.pibiti.model.AvaliacaoMaturidade;
import br.unitins.pibiti.model.Nit;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
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

    public PanacheQuery<AvaliacaoMaturidade> findListByNit(Nit nit, Sort sort) {

        if (nit == null)
            return null;

        return find("nit = ?1", sort, nit);
    }

    public PanacheQuery<AvaliacaoMaturidade> findListByNitAndNivelMaturidade(Nit nit, String nivelMaturidade, Sort sort) {

        if (nit == null || nivelMaturidade == null)
            return null;

        return find("nit = ?1 AND UPPER(nivelMaturidade) LIKE ?2", sort, nit, "%" + nivelMaturidade.toUpperCase() + "%");
    }

    public AvaliacaoMaturidade findById(String id) {
        if (id == null)
            return null;

        return find("idAvaliacaoMaturidade = ?1", id).firstResult();
    }
}
