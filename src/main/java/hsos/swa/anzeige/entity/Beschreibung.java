package hsos.swa.anzeige.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Beschreibung extends PanacheEntityBase {

    @Id
    @GeneratedValue
    Long id;
    String beschreibung;

    public Beschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public Beschreibung() {

    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }
}
