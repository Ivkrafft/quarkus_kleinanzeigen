package hsos.swa.warenkorb.boundry;

import hsos.swa.benutzer.boundry.BenutzerAuth;
import hsos.swa.warenkorb.control.WarenkorbController;

import io.quarkus.security.identity.SecurityIdentity;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.Objects;

@Path("/api/v1/warenkorb")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
@Transactional(value = Transactional.TxType.REQUIRES_NEW)
public class WarenkorbRest {

    @Inject
    WarenkorbController warenkorbController;
    @Inject
    BenutzerAuth benutzerAuth;
    @Inject
    SecurityIdentity sc;
    @RolesAllowed("user")
    @POST
    public Response createWarenkorb(WarenkorbDTO warenkorbDTO) {
        warenkorbDTO.setBenutzerId(benutzerAuth.authenticated(sc.getPrincipal().getName()));
        WarenkorbDTO createdWarenkorb = warenkorbController.createWarenkorb(warenkorbDTO);
        return Response.status(Response.Status.CREATED).entity(createdWarenkorb).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed("admin")
    public Response getWarenkorbById(@PathParam("id") long id) {
        WarenkorbDTO warenkorbDTO = warenkorbController.getWarenkorbById(id);
        if (warenkorbDTO != null) {
            return Response.ok(warenkorbDTO).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @RolesAllowed("admin")
    public Response getAllWarenkoerbe() {
        List<WarenkorbDTO> warenkorbDTOList = warenkorbController.getAllWarenkoerbe();
        return Response.ok(warenkorbDTOList).build();
    }
/*
    @GET
    @RolesAllowed("user")
    public Response getMyWarenkorb() {
        WarenkorbDTO warenkorbDTO = warenkorbController.getWarenkorbByBenutzerId(benutzerAuth.authenticated(sc.getPrincipal().getName()));
        return Response.ok(warenkorbDTO).build();
    }
*/
    @PUT
    @RolesAllowed("user")
    public Response updateWarenkorb(WarenkorbDTO warenkorbDTO) {
        if (!Objects.equals(warenkorbDTO.getBenutzerId(), benutzerAuth.authenticated(sc.getPrincipal().getName()))) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        warenkorbDTO.setBenutzerId(benutzerAuth.authenticated(sc.getPrincipal().getName()));
        WarenkorbDTO updatedWarenkorb = warenkorbController.updateWarenkorb(warenkorbDTO);
        if (updatedWarenkorb != null) {
            return Response.ok(updatedWarenkorb).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("admin")
    public Response deleteWarenkorb(@PathParam("id") long id) {
        boolean deleted = warenkorbController.deleteWarenkorb(id);
        if (deleted) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
