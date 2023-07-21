package hsos.swa.benutzer.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Adresse extends PanacheEntityBase {
    @Id
    @GeneratedValue
    long id;
    String strasse;
    int hausnummer;
    String zusatz;
    String ort;
    int postleitzahl;

    public Adresse(String strasse, int hausnummer, String zusatz, String ort, int postleitzahl) {
        this.strasse = strasse;
        this.hausnummer = hausnummer;
        this.zusatz = zusatz;
        this.ort = ort;
        this.postleitzahl = postleitzahl;
    }

    public Adresse() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public int getHausnummer() {
        return hausnummer;
    }

    public void setHausnummer(int hausnummer) {
        this.hausnummer = hausnummer;
    }

    public String getZusatz() {
        return zusatz;
    }

    public void setZusatz(String zusatz) {
        this.zusatz = zusatz;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public int getPostleitzahl() {
        return postleitzahl;
    }

    public void setPostleitzahl(int postleitzahl) {
        this.postleitzahl = postleitzahl;
    }
}
