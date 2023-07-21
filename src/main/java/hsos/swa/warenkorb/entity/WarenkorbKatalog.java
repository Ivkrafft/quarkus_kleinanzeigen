package hsos.swa.warenkorb.entity;

import java.util.List;

public interface WarenkorbKatalog {
    List<Warenkorb> getAllWarenkoerbe();
    Warenkorb getWarenkorb(long warenkorbId);
    void addWarenkorb(Warenkorb warenkorb);
    void updateWarenkorb(long warenkorbId, Warenkorb warenkorb);
    void deleteWarenkorb(long warenkorbId);
    Warenkorb getWarenkorbByBenutzerId(Long benutzerId);
}