package hsos.swa.benutzer.control;

import hsos.swa.benutzer.boundry.AdresseDTO;
import hsos.swa.benutzer.boundry.BenutzerDTO;
import hsos.swa.benutzer.entity.Adresse;
import hsos.swa.benutzer.entity.Benutzer;
import hsos.swa.benutzer.entity.BenutzerKatalog;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
@ApplicationScoped
public class BenutzerController {
    @Inject
    BenutzerKatalog benutzerRepository;

    @Transactional
    public BenutzerDTO createBenutzer(BenutzerDTO benutzerDTO) {
        Benutzer benutzer = convertToEntity(benutzerDTO);
        benutzerRepository.addBenutzer(benutzer);
        return convertToDTO(benutzer);
    }

    public BenutzerDTO getBenutzerById(long id) {
        Benutzer benutzer = benutzerRepository.getBenutzer(id);
        if (benutzer != null) {
            return convertToDTO(benutzer);
        } else {
            return null;
        }
    }

    public BenutzerDTO getBenutzerByName(String name) {
        Benutzer benutzer = benutzerRepository.getBenutzerByName(name);
        if (benutzer != null) {
            return convertToDTO(benutzer);
        } else {
            return null;
        }
    }

    public List<BenutzerDTO> getAllBenutzer() {
        List<Benutzer> benutzers = benutzerRepository.listBenutzer();
        return convertToDTOList(benutzers);
    }

    @Transactional
    public boolean updateBenutzer(BenutzerDTO benutzerDTO) {
        benutzerRepository.updateBenutzer(benutzerDTO.id,convertToEntity(benutzerDTO));
        return true;
    }

    @Transactional
    public boolean deleteBenutzer(long id) {
        Benutzer benutzer = benutzerRepository.getBenutzer(id);
        if (benutzer != null) {
            benutzerRepository.deleteBenutzer(id);
            return true;
        } else {
            return false;
        }
    }

    private BenutzerDTO convertToDTO(Benutzer benutzer) {
        return new BenutzerDTO(benutzer.getId(),benutzer.getKeycloakId(), benutzer.getName(), benutzer.getNachname(), benutzer.getEmail(), convertToDTO(benutzer.getAdresse()));
    }

    private AdresseDTO convertToDTO(Adresse adresse) {
        return new AdresseDTO(adresse.getStrasse(), adresse.getHausnummer(), adresse.getZusatz(), adresse.getOrt(), adresse.getPostleitzahl());
    }

    private List<BenutzerDTO> convertToDTOList(List<Benutzer> benutzers) {
        return benutzers.stream().map(benutzer -> new BenutzerDTO(benutzer.getId(),benutzer.getKeycloakId(), benutzer.getName(), benutzer.getNachname(), benutzer.getEmail(), convertToDTO(benutzer.getAdresse())))
                .collect(Collectors.toList());
    }

    private Benutzer convertToEntity(BenutzerDTO benutzerDTO){
        return new Benutzer(benutzerDTO.getKeycloakId(),benutzerDTO.getName(),benutzerDTO.getNachname(),benutzerDTO.getEmail(),convertToEntity(benutzerDTO.getAdresseDTO()));
    }

    private Adresse convertToEntity(AdresseDTO adresseDTO){
        return new Adresse(adresseDTO.getStrasse(),adresseDTO.getHausnummer(),adresseDTO.getZusatz(),adresseDTO.getOrt(),adresseDTO.getPostleitzahl());
    }

    public Long authenticated(String keycloakId){
        return benutzerRepository.getBenutzerByKeycloakId(keycloakId).getId();
    }
}