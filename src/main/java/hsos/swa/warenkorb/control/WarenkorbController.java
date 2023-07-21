package hsos.swa.warenkorb.control;

import hsos.swa.warenkorb.boundry.WarenkorbDTO;
import hsos.swa.warenkorb.entity.AnzeigeId;
import hsos.swa.warenkorb.entity.Warenkorb;
import hsos.swa.warenkorb.entity.WarenkorbKatalog;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;
@ApplicationScoped
public class WarenkorbController {

    @Inject
    WarenkorbKatalog warenkorbRepository;

    public WarenkorbDTO createWarenkorb(WarenkorbDTO warenkorbDTO) {
        List<AnzeigeId> anzeigeIds = warenkorbDTO.getAnzeigeIds().stream().map(AnzeigeId::new).collect(Collectors.toList());
        Warenkorb warenkorb = new Warenkorb(anzeigeIds,warenkorbDTO.getBenutzerId());
        warenkorbRepository.addWarenkorb(warenkorb);
        return convertToDTO(warenkorb);
    }

    public WarenkorbDTO getWarenkorbById(long id) {
        Warenkorb warenkorb = warenkorbRepository.getWarenkorb(id);
        if (warenkorb != null) {
            return convertToDTO(warenkorb);
        } else {
            return null;
        }
    }

    public WarenkorbDTO getWarenkorbByBenutzerId(long id) {
        Warenkorb warenkorb = warenkorbRepository.getWarenkorbByBenutzerId(id);
        if (warenkorb != null) {
            return convertToDTO(warenkorb);
        } else {
            return null;
        }
    }

    public List<WarenkorbDTO> getAllWarenkoerbe() {
        List<Warenkorb> warenkorbList = warenkorbRepository.getAllWarenkoerbe();
        return convertWarenkorbToDTOList(warenkorbList);
    }

    public WarenkorbDTO updateWarenkorb(WarenkorbDTO warenkorbDTO) {
        Warenkorb warenkorb = warenkorbRepository.getWarenkorb(warenkorbDTO.getId());
        if (warenkorb != null) {
            warenkorb.setBenutzerId(warenkorbDTO.getBenutzerId());
            warenkorb.setAnzeigeIds(warenkorbDTO.getAnzeigeIds());
            return convertToDTO(warenkorb);
        } else {
            return null;
        }
    }

    public boolean deleteWarenkorb(long id) {
        Warenkorb warenkorb = warenkorbRepository.getWarenkorb(id);
        if (warenkorb != null) {
            warenkorbRepository.deleteWarenkorb(id);
            return true;
        } else {
            return false;
        }
    }

    private WarenkorbDTO convertToDTO(Warenkorb warenkorb) {
        return new WarenkorbDTO(warenkorb.getId(), warenkorb.getAnzeigeIds(), warenkorb.getBenutzerId());
    }

    private List<WarenkorbDTO> convertWarenkorbToDTOList(List<Warenkorb> warenkorbList) {
        return warenkorbList.stream()
                .map(warenkorb -> new WarenkorbDTO(warenkorb.getId(), warenkorb.getAnzeigeIds(), warenkorb.getBenutzerId()))
                .collect(Collectors.toList());
    }
}