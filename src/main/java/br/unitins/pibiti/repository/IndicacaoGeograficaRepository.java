package br.unitins.pibiti.repository;

import br.unitins.pibiti.model.IndicacaoGeografica;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class IndicacaoGeograficaRepository implements PanacheRepository<IndicacaoGeografica> {

}
