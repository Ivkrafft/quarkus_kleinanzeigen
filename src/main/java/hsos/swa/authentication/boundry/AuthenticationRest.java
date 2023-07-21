package hsos.swa.authentication.boundry;

import hsos.swa.authentication.control.AuthenticationCrontrol;
import io.quarkus.security.identity.SecurityIdentity;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.idm.RoleRepresentation;

import java.util.List;
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/api/users")
public class AuthenticationRest {
        @Inject
        AuthenticationCrontrol authenticationCrontrol;


        @POST
        public Response register(String username) {
                authenticationCrontrol.createUsers(username, "password");
                return Response.ok("regestriert").build();
        }
        @GET
        @Path("/roles")
        public Response getAllRoles(){
                List<RoleRepresentation> roles = authenticationCrontrol.getRoles();
                if(roles != null){
                        return Response.ok(roles).build();
                }
                return Response.status(Response.Status.NOT_FOUND).build();
        }
}
