package br.unitins.pibiti.repository;

import br.unitins.pibiti.model.DimensaoAvaliacao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DimensaoAvaliacaoRepository implements PanacheRepository<DimensaoAvaliacao> {
    
}
