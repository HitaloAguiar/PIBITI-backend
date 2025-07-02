package br.unitins.pibiti.resource;

import br.unitins.pibiti.dto.nit.NitDTO;
import br.unitins.pibiti.dto.nit.NitResponseDTO;
import br.unitins.pibiti.service.nit.NitService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/nits")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class NitResource {
    
    @Inject
    NitService nitService;

    @GET
    @Path("/{id}")
    public NitResponseDTO getNit(@PathParam("id") Long id) throws NotFoundException {

        return nitService.getNit(id);
    }

    @POST
    public Response cadastrar(NitDTO nitDTO) {

        NitResponseDTO nitResponseDTO = nitService.cadastrar(nitDTO);
         
        return Response.status(Status.CREATED).entity(nitResponseDTO).build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") Long id, NitDTO nitDTO) {

        NitResponseDTO nitResponseDTO = nitService.atualizar(id, nitDTO);

        return Response.status(Status.CREATED).entity(nitResponseDTO).build();
    }
}
