package br.unitins.pibiti.repository;

import java.util.List;

import br.unitins.pibiti.model.Dimensao;
import br.unitins.pibiti.model.Variavel;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class VariavelRepository implements PanacheRepository<Variavel> {
    
    public List<Variavel> findByDimensao(Dimensao dimensao) {
        
        if (dimensao == null)
            return null;

        return find("dimensao = ?1", dimensao).list();
    }
}
