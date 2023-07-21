package hsos.swa.anzeige.entity;


import java.util.List;

public interface AnzeigeKatalog {

    List<Anzeige> listAnzeigen();
    Anzeige getAnzeige(long id);

    boolean addAnzeige(Anzeige anzeige);
    boolean deleteAnzeige(long id);
    Anzeige findAnzeigeByName(String name);
}