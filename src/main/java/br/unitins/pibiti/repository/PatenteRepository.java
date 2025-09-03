package br.unitins.pibiti.repository;

import br.unitins.pibiti.model.Patente;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PatenteRepository implements PanacheRepository<Patente> {

}
