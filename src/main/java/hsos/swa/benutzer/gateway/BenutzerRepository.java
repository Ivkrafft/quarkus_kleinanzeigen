package hsos.swa.benutzer.gateway;

import hsos.swa.benutzer.entity.Benutzer;
import hsos.swa.benutzer.entity.BenutzerKatalog;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import jakarta.enterprise.context.RequestScoped;
import jakarta.transaction.Transactional;
import java.util.List;
@RequestScoped
@Transactional(value = Transactional.TxType.REQUIRED)
public class BenutzerRepository implements BenutzerKatalog, PanacheRepository<Benutzer> {
    @Override
    public List<Benutzer> listBenutzer() {
        return listAll();
    }

    @Override
    public Benutzer getBenutzer(long benutzernummer) {
        return findById(benutzernummer);
    }

    @Override
    public void addBenutzer(Benutzer benutzer) {
        persist(benutzer);
    }

    @Override
    public boolean updateBenutzer(long benutzernummer, Benutzer benutzer) {
        Benutzer oldBenutzer = this.findById(benutzernummer);
        if (oldBenutzer == null){
            return false;
        }
        oldBenutzer.setName(benutzer.getName());
        oldBenutzer.setNachname(benutzer.getNachname());
        oldBenutzer.setAdresse(benutzer.getAdresse());
        return true;
    }

    @Override
    public boolean deleteBenutzer(long benutzernummer) {
        return deleteById(benutzernummer);
    }

    @Override
    public Benutzer getBenutzerByName(String Nachname) {
        return find("nachname",Nachname).firstResult();
    }
    @Override
    public Benutzer getBenutzerByKeycloakId(String KeycloakId) {
        return find("keycloakId",KeycloakId).firstResult();
    }
}