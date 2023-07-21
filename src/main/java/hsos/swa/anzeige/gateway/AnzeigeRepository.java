package hsos.swa.anzeige.gateway;

import hsos.swa.anzeige.entity.Anzeige;
import hsos.swa.anzeige.entity.AnzeigeKatalog;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.transaction.Transactional;
import java.util.List;

@RequestScoped
@Transactional(value = Transactional.TxType.REQUIRED)
public class AnzeigeRepository implements AnzeigeKatalog, PanacheRepository<Anzeige> {


    @Override
    public List<Anzeige> listAnzeigen() {
        return listAll();
    }

    @Override
    public Anzeige getAnzeige(long id) {
        return findById(id);
    }

    @Override
    public boolean addAnzeige(Anzeige anzeige) {
        anzeige.persist();
        return true;
    }

    @Override
    public boolean deleteAnzeige(long id) {
        return deleteById(id);
    }

    @Override
    public Anzeige findAnzeigeByName(String name) {
        return find("name",name).firstResult();
    }
}