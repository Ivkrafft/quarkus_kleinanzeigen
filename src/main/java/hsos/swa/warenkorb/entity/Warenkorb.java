package hsos.swa.warenkorb.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Warenkorb extends PanacheEntityBase{

    @Id
    @GeneratedValue
    Long id;
    @OneToMany(cascade = CascadeType.ALL)
    List<AnzeigeId> anzeigeIds;
    Long benutzerId;

    public Warenkorb(List<AnzeigeId> anzeigeIds, Long benutzerId) {
        this.anzeigeIds = anzeigeIds;
        this.benutzerId = benutzerId;
    }

    public Warenkorb(){}

    public List<Long> getAnzeigeIds() {
    List<Long> anzeigeIdsDTO = new java.util.ArrayList<>();
        for (AnzeigeId anzeigeId : anzeigeIds) {
            anzeigeIdsDTO.add(anzeigeId.getAnzeigeId());
        }
        return anzeigeIdsDTO;
    }

    public void setAnzeigeIds(List<Long> anzeigeIdsDTO) {
        for (Long anzeigeId : anzeigeIdsDTO) {
            this.anzeigeIds.add(new AnzeigeId(anzeigeId));
        }
    }

    public long getBenutzerId() {
        return benutzerId;
    }

    public void setBenutzerId(long benutzerId) {
        this.benutzerId = benutzerId;
    }

    public long getId() {
        return this.id;
    }
}
