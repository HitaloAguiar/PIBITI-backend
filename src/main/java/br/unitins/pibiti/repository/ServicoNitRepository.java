package br.unitins.pibiti.repository;

import br.unitins.pibiti.model.ServicoNit;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ServicoNitRepository implements PanacheRepository<ServicoNit> {
    
}
