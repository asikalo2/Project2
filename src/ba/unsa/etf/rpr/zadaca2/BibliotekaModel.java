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


    public ArrayList<String> dajKnjige(){
        ArrayList<String> lista = new ArrayList<>();
        String s = "";

        for (Knjiga k : knjige) {
            s = datumString(k.getDatumIzdanja());
            lista.add(k.getAutor() + ", " + k.getNaslov() + ", " + k.getBrojStranica() + ", " + s);
        }
        return lista;
    }

    public String datumString(LocalDate d){
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

    /*Ovu funkcionalnost treba realizirati pravljenjem metode ​deleteKnjiga()​​ u klasi BibliotekaModel koja će iz liste
    knjiga izbaciti trenutnu knjigu, a zatim postaviti atribut trenutnaKnjiga na ​null​​.*/
    void napuni() {
        knjige.add(new Knjiga("Meša Selimović", "Tvrđava", "abcd", 500));
        knjige.add(new Knjiga("Ivo Andrić", "Travnička hronika", "abcd", 500));
        knjige.add(new Knjiga("J. K. Rowling", "Harry Potter", "abcd", 500));
        //trenutnaKnjiga.set(null);
    }

    public void deleteKnjiga(Knjiga k){
        int i=0;
        for(i=0; i<knjige.size(); i++){
            if(knjige.get(i).equals(k)) {
                knjige.remove(i);
                trenutnaKnjiga = null;
            }
        }
    }
}
