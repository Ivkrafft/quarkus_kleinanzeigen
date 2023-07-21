package hsos.swa.benutzer.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

@Entity
public class Benutzer extends PanacheEntityBase {
    @Id
    @GeneratedValue
    Long id;
    String keycloakId;
    String name;
    String nachname;
    String email;
    @OneToOne(cascade = CascadeType.ALL)
    Adresse adresse;



    public Benutzer(String keycloakId,String name, String nachname,String email, Adresse adresse) {
        this.keycloakId = keycloakId;
        this.name = name;
        this.nachname = nachname;
        this.adresse = adresse;
        this.email = email;
    }

    public Benutzer() {

    }
    public String getKeycloakId() {
        return keycloakId;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }
}
