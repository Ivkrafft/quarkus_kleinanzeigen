package hsos.swa.benutzer.boundry;

public class AdresseDTO {
    String strasse;
    int hausnummer;
    String zusatz;
    String ort;
    int postleitzahl;

    public AdresseDTO(String strasse, int hausnummer, String zusatz, String ort, int postleitzahl) {
        this.strasse = strasse;
        this.hausnummer = hausnummer;
        this.zusatz = zusatz;
        this.ort = ort;
        this.postleitzahl = postleitzahl;
    }
    public AdresseDTO(){}

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
