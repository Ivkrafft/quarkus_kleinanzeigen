package hsos.swa.benutzer.boundry;

import hsos.swa.benutzer.control.BenutzerController;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
@ApplicationScoped
public class BenutzerAuth {
    @Inject
    BenutzerController benutzerController;
    public Long authenticated(String keycloakId){
        return benutzerController.authenticated(keycloakId);
    }
}
