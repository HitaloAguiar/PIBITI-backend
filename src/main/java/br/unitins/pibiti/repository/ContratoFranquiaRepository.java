package br.unitins.pibiti.repository;

import br.unitins.pibiti.model.ContratoFranquia;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ContratoFranquiaRepository implements PanacheRepository<ContratoFranquia> {

}
