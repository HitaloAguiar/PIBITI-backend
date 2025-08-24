package br.unitins.pibiti.repository;

import br.unitins.pibiti.model.AuditoriaRecuperacaoSenha;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AuditoriaRecuperacaoSenhaRepository implements PanacheRepository<AuditoriaRecuperacaoSenha> {
    
}
