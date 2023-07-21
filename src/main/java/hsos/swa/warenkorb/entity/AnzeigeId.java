package hsos.swa.warenkorb.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class AnzeigeId extends PanacheEntityBase{
    @Id
    @GeneratedValue
    Long id;

    Long anzeigeId;

    public AnzeigeId(Long anzeigeId) {
        this.anzeigeId = anzeigeId;
    }

    public AnzeigeId() {
    }

    public Long getId() {
        return id;
    }

    public Long getAnzeigeId() {
        return anzeigeId;
    }
}
