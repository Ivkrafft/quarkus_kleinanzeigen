package hsos.swa.anzeige.control;

import hsos.swa.anzeige.boundry.AnzeigeDTO;
import hsos.swa.anzeige.entity.Anzeige;
import hsos.swa.anzeige.entity.AnzeigeKatalog;
import hsos.swa.anzeige.gateway.AnzeigeRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class AnzeigeController {

    @Inject
    AnzeigeRepository anzeigeRepository;

    public AnzeigeDTO createAnzeige(AnzeigeDTO anzeigeDTO) {
        Anzeige anzeige = new Anzeige(anzeigeDTO.getName(), anzeigeDTO.getPreis(),anzeigeDTO.getBenutzerId() ,anzeigeDTO.getBeschreibung());
        anzeigeRepository.addAnzeige(anzeige);
        return convertToDTO(anzeige);
    }

    public AnzeigeDTO getAnzeigeById(long id) {
        Anzeige anzeige = anzeigeRepository.getAnzeige(id);
        if (anzeige != null) {
            return convertToDTO(anzeige);
        } else {
            return null;
        }
    }

    public AnzeigeDTO getAnzeigeByName(String name) {
        Anzeige anzeige = anzeigeRepository.findAnzeigeByName(name);
        if (anzeige != null) {
            return convertToDTO(anzeige);
        } else {
            return null;
        }
    }

    public List<AnzeigeDTO> getAllAnzeigen() {
        List<Anzeige> anzeigen = anzeigeRepository.listAnzeigen();
        return convertToDTOList(anzeigen);
    }

    public AnzeigeDTO updateAnzeige(AnzeigeDTO anzeigeDTO) {
        Anzeige anzeige = anzeigeRepository.getAnzeige(anzeigeDTO.getId());
        if (anzeige != null) {
            anzeige.setName(anzeigeDTO.getName());
            anzeige.setBeschreibung(anzeigeDTO.getBeschreibung());
            anzeige.setPreis(anzeigeDTO.getPreis());
            return convertToDTO(anzeige);
        } else {
            return null;
        }
    }

    public boolean deleteAnzeige(long id) {
        Anzeige anzeige = anzeigeRepository.getAnzeige(id);
        if (anzeige != null){
            return anzeigeRepository.deleteAnzeige(id);
        }else {
            return false;
        }
    }

    private AnzeigeDTO convertToDTO(Anzeige anzeige) {
        return new AnzeigeDTO(anzeige.getId(), anzeige.getName(), anzeige.getPreis(), anzeige.getBenutzerId(), anzeige.getBeschreibung());
    }

    private List<AnzeigeDTO> convertToDTOList(List<Anzeige> anzeigen) {
        return anzeigen.stream()
                .map(anzeige -> new AnzeigeDTO(anzeige.getId(), anzeige.getName(), anzeige.getPreis(), anzeige.getBenutzerId(), anzeige.getBeschreibung()))
                .collect(Collectors.toList());
    }

}