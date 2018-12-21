package ba.unsa.etf.rpr.zadaca2;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;
import java.util.Date;

public class Knjiga {
/*Pored postojećih atributa, klasa Knjiga treba imati i atribut datumIzdanja. Metoda
getDatumIzdanja treba vraćati vrijednost tipa java.time.LocalDate.
*/

    private SimpleStringProperty autor = new SimpleStringProperty("");
    private SimpleStringProperty naslov = new SimpleStringProperty("");
    private SimpleStringProperty isbn = new SimpleStringProperty("");
    private SimpleIntegerProperty brojStranica = new SimpleIntegerProperty(0);
    private java.time.LocalDate datumIzdanja;
    public Knjiga() {}

    public Knjiga(String a, String n, String i, int b) {
        autor = new SimpleStringProperty(a);
        naslov = new SimpleStringProperty(n);
        isbn = new SimpleStringProperty(i);
        brojStranica = new SimpleIntegerProperty(b);
        datumIzdanja = LocalDate.now();
    }

    public Knjiga(String a, String n, String i, int b, LocalDate d) {
        autor = new SimpleStringProperty(a);
        naslov = new SimpleStringProperty(n);
        isbn = new SimpleStringProperty(i);
        brojStranica = new SimpleIntegerProperty(b);
        datumIzdanja = d;
    }

    public String getIsbn() {

        return isbn.get();
    }

    public SimpleStringProperty isbnProperty() {

        return isbn;
    }

    public void setIsbn(String isbn) {

        this.isbn.set(isbn);
    }


    public String getNaslov() {

        return naslov.get();
    }

    public SimpleStringProperty naslovProperty() {
        return naslov;
    }

    public void setNaslov(String naslov) {

        this.naslov.set(naslov);
    }


    public String getAutor() {

        return autor.get();
    }

    public SimpleStringProperty autorProperty() {

        return autor;
    }

    public void setAutor(String autor) {
        this.autor.set(autor);
    }

    public int getBrojStranica() {

        return brojStranica.get();
    }

    public SimpleIntegerProperty brojStranicaProperty() {
        return brojStranica;
    }

    public void setBrojStranica(int brojStranica) {
        this.brojStranica.set(brojStranica);
    }

    @Override
    public String toString() {
        return autor.get() + ", " + naslov.get() + ", " + isbn.get() + ", " +
                brojStranica.get() + ", " + datumIzdanja.getDayOfMonth() + ". " + datumIzdanja.getMonthValue() + ". " + datumIzdanja.getYear();
    }


    public LocalDate getDatumIzdanja() {
        return datumIzdanja;
    }

    public void setDatumIzdanja(LocalDate datumIzdanja) {
        this.datumIzdanja = datumIzdanja;
    }
}
