package br.unitins.pibiti.repository;

import java.util.List;

import br.unitins.pibiti.model.AvaliacaoMaturidade;
import br.unitins.pibiti.model.VariavelAvaliacao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class VariavelAvaliacaoRepository implements PanacheRepository<VariavelAvaliacao> {

    public List<VariavelAvaliacao> findByAvaliacao(AvaliacaoMaturidade avaliacaoMaturidade) {

        if (avaliacaoMaturidade == null)
            return null;

        return find("avaliacao = ?1", avaliacaoMaturidade).list();
    }
}
