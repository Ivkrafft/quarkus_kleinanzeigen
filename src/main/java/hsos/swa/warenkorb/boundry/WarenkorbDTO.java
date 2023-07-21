package hsos.swa.warenkorb.boundry;

import java.util.List;
public class WarenkorbDTO {
    long id;
    List<Long> anzeigeIds;
    Long benutzerId;

    public WarenkorbDTO(long id, List<Long> anzeigeIds, Long benutzerId) {
        this.id = id;
        this.anzeigeIds = anzeigeIds;
        this.benutzerId = benutzerId;
    }

    public WarenkorbDTO(){}
    public void setId(long id) {
        this.id = id;
    }

    public List<Long> getAnzeigeIds() {
        return anzeigeIds;
    }

    public void setanzeigeIds(List<Long> anzeigeIds) {
        this.anzeigeIds = anzeigeIds;
    }

    public Long getBenutzerId() {
        return benutzerId;
    }

    public void setBenutzerId(Long benutzerId) {
        this.benutzerId = benutzerId;
    }

    public long getId(){
        return this.id;
    }

    public void initializeAnzeigeIds() {
        anzeigeIds.size(); // Trigger the initialization of the anzeigeIds collection
    }
}