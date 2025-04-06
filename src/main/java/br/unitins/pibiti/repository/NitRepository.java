package br.unitins.pibiti.repository;

import br.unitins.pibiti.model.Nit;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class NitRepository implements PanacheRepository<Nit> {
    
}
