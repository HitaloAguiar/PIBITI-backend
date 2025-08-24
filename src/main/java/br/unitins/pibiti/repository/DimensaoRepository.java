package br.unitins.pibiti.repository;

import br.unitins.pibiti.model.Dimensao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DimensaoRepository implements PanacheRepository<Dimensao> {
    
}
