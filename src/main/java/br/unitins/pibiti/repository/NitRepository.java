package br.unitins.pibiti.repository;

import br.unitins.pibiti.model.Nit;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class NitRepository implements PanacheRepository<Nit> {
    
    public Nit findByCnpjAndSenha(String cnpj, String senha) {

        if (cnpj == null || senha == null)
            return null;

        return find("cnpj = ?1 AND senha = ?2", cnpj, senha).firstResult();
    }

    public Nit findByEmailAndSenha(String email, String senha) {

        if (email == null || senha == null)
            return null;
        
        return find("email = ?1 AND senha = ?2", email, senha).firstResult();
    }

    public Nit findByEmail(String email) {
        if (email == null){
            return null;
        }

        return find("email = ?1", email).firstResult();
    }

    public Nit findByCnpj(String cnpj) {
        if (cnpj == null){
            return null;
        }

        return find("cnpj = ?1", cnpj).firstResult();
    }
}
