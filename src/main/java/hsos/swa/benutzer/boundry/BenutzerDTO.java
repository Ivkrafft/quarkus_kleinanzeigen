package hsos.swa.benutzer.boundry;

public class BenutzerDTO {
    public long id;
    public String name;
    public String keycloakId;
    public String nachname;
    public String email;
    public AdresseDTO adresseDTO;

    public BenutzerDTO(long id, String keycloakId,String name, String nachname,String email, AdresseDTO adresseDTO) {
        this.id = id;
        this.keycloakId = keycloakId;
        this.name = name;
        this.nachname = nachname;
        this.adresseDTO = adresseDTO;
        this.email = email;
    }

    public BenutzerDTO(){

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

    public AdresseDTO getAdresseDTO() {
        return adresseDTO;
    }

    public void setAdresseDTO(AdresseDTO adresseDTO) {
        this.adresseDTO = adresseDTO;
    }
}