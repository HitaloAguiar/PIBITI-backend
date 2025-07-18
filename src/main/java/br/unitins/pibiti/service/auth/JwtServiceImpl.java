package br.unitins.pibiti.service.auth;

import java.time.Duration;
import java.time.Instant;

import br.unitins.pibiti.model.Nit;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class JwtServiceImpl implements JwtService {

    private static final Duration EXPIRATION_TIME = Duration.ofHours(24);
    
    @Override
    public String generateJwt(Nit nit) {

        Instant now = Instant.now();
        Instant expiryDate = now.plus(EXPIRATION_TIME);
        
        return Jwt.issuer("unitins-jwt")
            .subject(nit.getCnpj())
            .expiresAt(expiryDate)
            .sign();
    }
}
