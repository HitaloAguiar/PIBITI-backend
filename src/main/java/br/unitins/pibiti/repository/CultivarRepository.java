package br.unitins.pibiti.repository;

import br.unitins.pibiti.model.Cultivar;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CultivarRepository implements PanacheRepository<Cultivar> {

}
