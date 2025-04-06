package br.unitins.pibiti.repository;

import br.unitins.pibiti.model.AvaliacaoMaturidade;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AvaliacaoMaturidadeRepository implements PanacheRepository<AvaliacaoMaturidade> {
    
}
