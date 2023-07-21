package hsos.swa.warenkorb.gateway;

import hsos.swa.benutzer.entity.Benutzer;
import hsos.swa.warenkorb.entity.Warenkorb;
import hsos.swa.warenkorb.entity.WarenkorbKatalog;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import jakarta.enterprise.context.RequestScoped;
import jakarta.transaction.Transactional;
import java.util.List;

@RequestScoped
@Transactional(value = Transactional.TxType.REQUIRED)
public class WarenkorbRepository implements WarenkorbKatalog, PanacheRepository<Warenkorb> {

    @Override
    public List<Warenkorb> getAllWarenkoerbe() {
        return listAll();
    }

    @Override
    public Warenkorb getWarenkorb(long warenkorbId) {
        return findById(warenkorbId);
    }

    @Override
    public void addWarenkorb(Warenkorb warenkorb) {
        persist(warenkorb);
    }

    @Override
    public void updateWarenkorb(long warenkorbId, Warenkorb warenkorb) {
        Warenkorb oldWarenkorb = this.findById(warenkorbId);
        if (oldWarenkorb == null){return;}
        oldWarenkorb.setBenutzerId(warenkorb.getBenutzerId());
        oldWarenkorb.setAnzeigeIds(warenkorb.getAnzeigeIds());
    }

    @Override
    public void deleteWarenkorb(long warenkorbId) {
        deleteById(warenkorbId);
    }
    @Override
    public Warenkorb getWarenkorbByBenutzerId(Long benutzerId) {
        return find("benutzerId",benutzerId).firstResult();
    }
}