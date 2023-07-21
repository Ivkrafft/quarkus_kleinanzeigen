package hsos.swa.anzeige.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import jakarta.persistence.*;

@Entity
public class Anzeige extends PanacheEntityBase {

    @Id
    @GeneratedValue
    Long id;
    String name;
    double preis;
    Long benutzerId;
    @OneToOne(cascade = CascadeType.ALL)
    Beschreibung beschreibung;

    public Anzeige(String name, double preis,Long benutzerId,Beschreibung beschreibung) {
        this.name = name;
        this.preis = preis;
        this.beschreibung = beschreibung;
        this.benutzerId = benutzerId;
    }

    public Anzeige() {

    }

    public Long getBenutzerId() {
        return benutzerId;
    }

    public void setBenutzerId(Long benutzerId) {
        this.benutzerId = benutzerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPreis() {
        return preis;
    }

    public void setPreis(double preis) {
        this.preis = preis;
    }

    public Beschreibung getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(Beschreibung beschreibung) {
        this.beschreibung = beschreibung;
    }
}
