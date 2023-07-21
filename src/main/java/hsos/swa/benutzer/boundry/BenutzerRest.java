package hsos.swa.benutzer.boundry;

import hsos.swa.benutzer.control.BenutzerController;
import io.quarkus.security.identity.SecurityIdentity;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/api/v1/benutzer")
@RequestScoped
@Transactional(value = Transactional.TxType.REQUIRES_NEW)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BenutzerRest {

    @Inject
    BenutzerController benutzerController;
    @Inject
    SecurityIdentity sc;

    @GET
    @Path("")
    @RolesAllowed("user")
    public Response getBenutzerByName(@QueryParam("name") String name) {
        if (name == null){
            return Response.ok(benutzerController.getAllBenutzer()).build();
        } else {
            BenutzerDTO benutzerDTO = benutzerController.getBenutzerByName(name);
            if (benutzerDTO != null) {
                return Response.ok(benutzerDTO).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        }
    }

    @POST
    @RolesAllowed("user")
    public Response createBenutzer(BenutzerDTO benutzerDTO) {
        benutzerDTO.setEmail(sc.getPrincipal().getName());
        BenutzerDTO createdBenutzer = benutzerController.createBenutzer(benutzerDTO);
        if (createdBenutzer != null) {
            return Response.status(Response.Status.CREATED).entity(createdBenutzer).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Path("/{id}")
    @RolesAllowed("user")
    public Response getBenutzerById(@PathParam("id") long id) {
        BenutzerDTO benutzerDTO = benutzerController.getBenutzerById(id);
        if (benutzerDTO != null) {
            return Response.ok(benutzerDTO).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @RolesAllowed("user")
    public Response updateBenutzer(BenutzerDTO benutzerDTO) {
        if (benutzerController.updateBenutzer(benutzerDTO)) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("{id}")
    @RolesAllowed("user")
    public Response deleteBenutzer(@PathParam("id") long id) {
        boolean deleted = benutzerController.deleteBenutzer(id);
        if (deleted) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}