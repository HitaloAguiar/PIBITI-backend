package br.unitins.pibiti.resource;

import java.util.List;

import org.eclipse.microprofile.jwt.JsonWebToken;

import br.unitins.pibiti.application.Result;
import br.unitins.pibiti.dto.propriedade_intelectual.direito_autoral.direito_autor.DireitoAutorDTO;
import br.unitins.pibiti.dto.propriedade_intelectual.direito_autoral.direito_autor.DireitoAutorResponseDTO;
import br.unitins.pibiti.service.propriedade_intelectual.direito_autoral.direito_autor.DireitoAutorService;
import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/propriedades-intelectuais/direitos-autor")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DireitoAutorResource {

    @Inject
    DireitoAutorService direitoAutorService;

    @Inject
    JsonWebToken jwt;

    @GET
    @Path("/{id}")
    public DireitoAutorResponseDTO getDireitoAutor(@PathParam("id") Long id) throws NotFoundException {
        return direitoAutorService.getDireitoAutor(id);
    }

    @GET
    public List<DireitoAutorResponseDTO> getAllPublico(@QueryParam("page") @DefaultValue("0") int page,
                                                                        @QueryParam("pageSize") @DefaultValue("10") int pageSize,
                                                                        @QueryParam("isAscending") @DefaultValue("false") Boolean isAscending) {

        return direitoAutorService.getAllPublico(page, pageSize, isAscending);
    }

    @GET
    @Path("/filtrado-por/{titulo}")
    public List<DireitoAutorResponseDTO> getAllPublicoFiltradoPorTitulo(@PathParam("titulo") String titulo,
                                                                                @QueryParam("page") @DefaultValue("0") int page,
                                                                                @QueryParam("pageSize") @DefaultValue("10") int pageSize,
                                                                                @QueryParam("isAscending") @DefaultValue("false") Boolean isAscending) {

        return direitoAutorService.getAllPublicoFiltradoPorTitulo(titulo, page, pageSize, isAscending);
    }

    @GET
    @Path("/nit/{id}")
    public List<DireitoAutorResponseDTO> getAllByNit(@PathParam("id") Long idNit,
                                                                @QueryParam("page") @DefaultValue("0") int page,
                                                                @QueryParam("pageSize") @DefaultValue("10") int pageSize,
                                                                @QueryParam("isAscending") @DefaultValue("false") Boolean isAscending) {

        return direitoAutorService.getAllByNit(idNit, page, pageSize, isAscending);
    }

    @GET
    @Path("/nit/{id}/filtrado-por/{titulo}")
    public List<DireitoAutorResponseDTO> getAllByNitFiltradoPorTitulo(@PathParam("id") Long idNit,
                                                                                @PathParam("titulo") String titulo,
                                                                                @QueryParam("page") @DefaultValue("0") int page,
                                                                                @QueryParam("pageSize") @DefaultValue("10") int pageSize,
                                                                                @QueryParam("isAscending") @DefaultValue("false") Boolean isAscending) {

        return direitoAutorService.getAllByNitFiltradoPorTitulo(idNit, titulo, page, pageSize, isAscending);
    }

    @POST
    @Authenticated
    public Response cadastrar(DireitoAutorDTO direitoAutorDTO) {

        Result result;

        try {

            return Response
                    .status(Response.Status.CREATED) // 201
                    .entity(direitoAutorService.cadastrar(direitoAutorDTO))
                    .build();
        } catch (ConstraintViolationException e) {

            result = new Result(e.getConstraintViolations());

            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(result)
                    .build();
        } catch (Exception e) {

            result = new Result(e.getMessage(), false);

            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(result)
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    @Authenticated
    public Response atualizar(@PathParam("id") Long id, DireitoAutorDTO direitoAutorDTO) {

        Result result;

        String cnpj = jwt.getSubject();

        try {

            direitoAutorService.atualizar(cnpj, id, direitoAutorDTO);

            return Response
                    .status(Response.Status.NO_CONTENT) // 204
                    .build();
        } catch (ConstraintViolationException e) {

            result = new Result(e.getConstraintViolations());

            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(result)
                    .build();
        } catch (Exception e) {

            result = new Result(e.getMessage(), false);

            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(result)
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Authenticated
    public Response deletarDireitoAutor(@PathParam("id") Long idDireitoAutor) throws IllegalArgumentException, NotFoundException {

        Result result;

        String cnpj = jwt.getSubject();

        try {
            direitoAutorService.deletarDireitoAutor(cnpj, idDireitoAutor);

            return Response
                    .status(Response.Status.NO_CONTENT)
                    .build();
        } catch (Exception e) {

            result = new Result(e.getMessage(), false);

            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(result)
                    .build();
        }
    }

}
