package br.unitins.pibiti.repository;

import br.unitins.pibiti.model.Responsavel;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ResponsavelRepository implements PanacheRepository<Responsavel> {
    
}
