package br.unitins.pibiti.repository;

import br.unitins.pibiti.model.VariavelAvaliacao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class VariavelAvaliacaoRepository implements PanacheRepository<VariavelAvaliacao> {
    
}
