package br.unitins.pibiti.repository;

import java.util.List;

import br.unitins.pibiti.model.AvaliacaoMaturidade;
import br.unitins.pibiti.model.DimensaoAvaliacao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DimensaoAvaliacaoRepository implements PanacheRepository<DimensaoAvaliacao> {
    
    public List<DimensaoAvaliacao> findByAvaliacao(AvaliacaoMaturidade avaliacaoMaturidade) {

        if (avaliacaoMaturidade == null)
            return null;

        return find("avaliacao = ?1", avaliacaoMaturidade).list();
    }
}
