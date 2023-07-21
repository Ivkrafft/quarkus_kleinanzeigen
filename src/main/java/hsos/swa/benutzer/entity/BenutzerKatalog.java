package hsos.swa.benutzer.entity;

import java.util.List;

public interface BenutzerKatalog {
    List<Benutzer> listBenutzer();
    Benutzer getBenutzer(long benutzernummer);
    void addBenutzer(Benutzer benutzer);
    boolean updateBenutzer(long benutzernummer, Benutzer benutzer);
    boolean deleteBenutzer(long benutzernummer);
    Benutzer getBenutzerByName(String name);
    Benutzer getBenutzerByKeycloakId(String KeycloakId);
}