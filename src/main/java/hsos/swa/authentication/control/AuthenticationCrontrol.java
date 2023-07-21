package hsos.swa.authentication.control;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.RoleRepresentation;
import jakarta.inject.Inject;
import org.keycloak.representations.idm.CredentialRepresentation;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.Collections;
import java.util.List;


@ApplicationScoped
public class AuthenticationCrontrol {
    @Inject
    Keycloak keycloak;

    public void createUsers(String username, String password) {
        UserRepresentation user = new UserRepresentation();
        user.setUsername(username);
        user.setEnabled(true);
        CredentialRepresentation credentials = new CredentialRepresentation();
        credentials.setType(CredentialRepresentation.PASSWORD);
        credentials.setValue(password);
        user.setCredentials(Collections.singletonList(credentials));
        keycloak.realm("quarkus").users().create(user);
    }

    public List<RoleRepresentation> getRoles() {
        return keycloak.realm("quarkus").roles().list();
    }
/*
    public Response login(String username, String password) {
        try {
            // Authentifizierung des Benutzers mit dem Keycloak-Admin-Client
            keycloak.tokenManager().getAccessToken();

            // Überprüfen Sie die Anmeldeinformationen des Benutzers
            CredentialRepresentation credentials = new CredentialRepresentation();
            credentials.setType(CredentialRepresentation.PASSWORD);
            credentials.setValue(password);

            UserRepresentation user = keycloak.realm("quarkus").users().search(username).get(0);
            if (keycloak.realm("quarkus").users().get(user.getId()).validatePassword(credentials)) {
                // Authentifizierung erfolgreich
                return Response.ok("Login erfolgreich").build();
            } else {
                // Ungültige Anmeldeinformationen
                return Response.status(Response.Status.UNAUTHORIZED).entity("Ungültige Anmeldeinformationen").build();
            }
        } catch (Exception e) {
            // Fehler beim Zugriff auf Keycloak-Admin-Client
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Fehler beim Zugriff auf Keycloak").build();
        }
    }

 */
}
