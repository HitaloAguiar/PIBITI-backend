package br.unitins.pibiti.service.auth;

import br.unitins.pibiti.model.Nit;

public interface JwtService {
    
    public String generateJwt(Nit nit);
}
