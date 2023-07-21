package hsos.swa.anzeige.boundry;

import hsos.swa.anzeige.entity.Beschreibung;

public class AnzeigeDTO {
    Long id;
    String name;
    double preis;
    Long benutzerId;
    Beschreibung beschreibung;

    public AnzeigeDTO(long id, String name, double preis,Long benutzerId ,Beschreibung beschreibung) {
        this.id = id;
        this.name = name;
        this.preis = preis;
        this.benutzerId = benutzerId;
        this.beschreibung = beschreibung;
    }

    public AnzeigeDTO() {
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
