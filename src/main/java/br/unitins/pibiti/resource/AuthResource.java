package br.unitins.pibiti.resource;

import br.unitins.pibiti.dto.nit.AuthNitDTO;
import br.unitins.pibiti.dto.nit.NitResponseDTO;
import br.unitins.pibiti.model.Nit;
import br.unitins.pibiti.service.auth.HashService;
import br.unitins.pibiti.service.auth.JwtService;
import br.unitins.pibiti.service.nit.NitService;
import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.core.SecurityContext;

@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthResource {
    
    @Inject
    HashService hashService;

    @Inject
    NitService nitService;

    @Inject
    JwtService jwtService;

    @POST
    public Response login(AuthNitDTO authNitDTO) {

        try {

            String hash = hashService.getHashSenha(authNitDTO.senha());

            Nit nit = nitService.getByLoginAndSenha(authNitDTO.cnpjOuEmail(), hash);

            if (nit == null) {

                return Response.status(Status.NOT_FOUND).entity("Login ou senha incorretos").build();
            }

            NitResponseDTO nitResponseDTO = new NitResponseDTO(nit);
            String token = jwtService.generateJwt(nit);

            return Response.ok(nitResponseDTO).header("Authorization", token).build();
        } catch (Exception e) {
            // TODO: handle exception

            throw e;
        }
    }

    @GET
    @Path("/perfil")
    @Authenticated
    public NitResponseDTO getNitLogado(@Context SecurityContext securityContext) {
        String login = securityContext.getUserPrincipal().getName();
        return nitService.getNitLogado(login);
    }
}
