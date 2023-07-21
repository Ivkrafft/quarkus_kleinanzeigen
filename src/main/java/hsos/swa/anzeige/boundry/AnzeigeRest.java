package hsos.swa.anzeige.boundry;

import hsos.swa.anzeige.control.AnzeigeController;

import hsos.swa.benutzer.boundry.BenutzerAuth;
import io.quarkus.security.identity.SecurityIdentity;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Objects;

@Path("/api/v1/anzeige")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
public class AnzeigeRest {

    @Inject
    AnzeigeController anzeigeController;
    @Inject
    BenutzerAuth benutzerAuth;
    @Inject
    SecurityIdentity sc;

    @POST
    @RolesAllowed("user")
    public Response createAnzeige(AnzeigeDTO anzeigeDTO) {
        anzeigeDTO.setBenutzerId(benutzerAuth.authenticated(sc.getPrincipal().getName()));
        AnzeigeDTO createdAnzeige = anzeigeController.createAnzeige(anzeigeDTO);
        if (createdAnzeige != null) {
            return Response.status(Response.Status.CREATED).entity(createdAnzeige).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Path("{id}")
    public Response getAnzeigeById(@PathParam("id") long id) {
        AnzeigeDTO anzeigeDTO = anzeigeController.getAnzeigeById(id);
        if (anzeigeDTO != null) {
            return Response.ok(anzeigeDTO).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("")
    public Response getAnzeigeByName(@QueryParam("name") String name) {
        if (name == null) {
            return Response.ok(anzeigeController.getAllAnzeigen()).build();
        } else {
            AnzeigeDTO AnzeigeDTO = anzeigeController.getAnzeigeByName(name);
            if (AnzeigeDTO != null) {
                return Response.ok(AnzeigeDTO).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

        }
    }

    @PUT
    @RolesAllowed("user")
    public Response updateAnzeige(AnzeigeDTO anzeigeDTO) {
        if (!Objects.equals(anzeigeDTO.getBenutzerId(), benutzerAuth.authenticated(sc.getPrincipal().getName()))) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        anzeigeDTO.setBenutzerId(benutzerAuth.authenticated(sc.getPrincipal().getName()));
        AnzeigeDTO updatedAnzeige = anzeigeController.updateAnzeige(anzeigeDTO);
        if (updatedAnzeige != null) {
            return Response.ok(updatedAnzeige).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("{id}")
    @RolesAllowed("user")
    public Response deleteAnzeige(@PathParam("id") long id) {
        if (!Objects.equals(anzeigeController.getAnzeigeById(id).getBenutzerId(), benutzerAuth.authenticated(sc.getPrincipal().getName()))) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        boolean deleted = anzeigeController.deleteAnzeige(id);
        if (deleted) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}
