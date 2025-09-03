package br.unitins.pibiti.repository;

import br.unitins.pibiti.model.RegistroProgramaComputador;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RegistroProgramaComputadorRepository implements PanacheRepository<RegistroProgramaComputador> {

}
