package br.unitins.pibiti.repository;

import br.unitins.pibiti.model.Variavel;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class VariavelRepository implements PanacheRepository<Variavel> {
    
}
