package br.unitins.pibiti.repository;

import java.util.List;

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

    public List<AvaliacaoMaturidade> findTop3ByNitOrderByImgDesc(Nit nit) {
        if (nit == null)
            return List.of();

        return find("nit = ?1", Sort.by("img").descending(), nit)
                .range(0, 2) // pega registros 0, 1 e 2 (3 registros)
                .list();
    }

    public AvaliacaoMaturidade findById(String id) {
        if (id == null)
            return null;

        return find("idAvaliacaoMaturidade = ?1", id).firstResult();
    }
}
