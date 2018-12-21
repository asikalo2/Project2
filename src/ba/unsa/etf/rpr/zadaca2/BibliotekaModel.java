package ba.unsa.etf.rpr.zadaca2;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.ArrayList;

public class BibliotekaModel {

    /*● U klasi BibliotekaModel treba dodati metodu dajKnjige koja vraća string sa svim
knjigama u biblioteci, onim redom kojim su date. Format stringa treba biti sljedeći:
Autor, Naslov, ISBN, 123, 01. 01. 1980
Autor, Naslov, ISBN, 123, 01. 01. 1980
...
pri čemu je 123 broj stranica. Obratite pažnju da datum treba biti u formatu "dan. mjesec.
godina" (sa početnim nulama, bez tačke iza godine), da se nakon svakog zareza nalazi i
jedan razmak, te da nema zareza na kraju reda.*/

    private ObservableList<Knjiga> knjige = FXCollections.observableArrayList();
    private ObjectProperty<Knjiga> trenutnaKnjiga = new SimpleObjectProperty<>();


    public String dajKnjige(){
        String s = "";
        String stringic = "";
        int i = 0;

        for (i=0; i<knjige.size(); i++){
            s = datumString(knjige.get(i).getDatumIzdanja());
            stringic += knjige.get(i).getAutor() + ", " + knjige.get(i).getNaslov() + ", " +
                    knjige.get(i).getBrojStranica() + ", " + s + "\n";
        }

        return stringic;
    }

    public String datumString(LocalDate d){
        if (d == null)
            return "";
        String datum;
        datum = d.getDayOfMonth() + ". " + d.getMonth() + ". " + d.getYear();
        return datum;
    }
    public ObjectProperty<Knjiga> trenutnaKnjigaProperty() {
        return trenutnaKnjiga;
    }

    public Knjiga getTrenutnaKnjiga() {
        return trenutnaKnjiga.get();
    }

    public void setTrenutnaKnjiga(Knjiga k) {
        trenutnaKnjiga.set(k);
    }

    public ObservableList<Knjiga> getKnjige() {
        return knjige;
    }

    public void ispisiKnjige() {
        System.out.println("Knjige su:");
        for (Knjiga k : knjige)
            System.out.println(k);
    }

    void napuni() {
        knjige.add(new Knjiga("Meša Selimović", "Tvrđava", "abcd", 500));
        knjige.add(new Knjiga("Ivo Andrić", "Travnička hronika", "abcd", 500));
        knjige.add(new Knjiga("J. K. Rowling", "Harry Potter", "abcd", 500));
        //trenutnaKnjiga.set(null);
    }

    public void deleteKnjiga(){
        int i=0;
        for(i=0; i<knjige.size(); i++){
            if(knjige.get(i).equals(trenutnaKnjiga)) {
                knjige.remove(trenutnaKnjiga);
                trenutnaKnjiga = null;
            }
        }
    }

    public void addKnjiga(Knjiga k){
        Knjiga knjiga = new Knjiga();
        knjiga.setAutor(k.getAutor());
        knjiga.setNaslov(k.getNaslov());
        knjiga.setBrojStranica(k.getBrojStranica());
        knjige.add(knjiga);
    }
}
