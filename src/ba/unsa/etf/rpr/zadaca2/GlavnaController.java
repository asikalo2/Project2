package ba.unsa.etf.rpr.zadaca2;

/*Program treba posjedovati glavni prozor sa: glavnim menijem, trakom sa alatima,
statusnom trakom, a centralni dio prozora treba biti TableView sa spiskom knjiga u
biblioteci. Forma se treba nalaziti u datoteci glavna.fxml a njen kontroler se treba zvati
GlavnaController.
*/



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTextFieldUI;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GlavnaController {
   // private static final BasicTextFieldUI Dialogs = ;
    @FXML
    private MenuBar menuBar;
    BibliotekaModel b;

    /* Opcija Save treba zapisati podatke u XML datoteku, a Open treba učitati iz XML datoteke.
    Potrebno je koristiti standardni open/save dijalog. Jedini podržani filter za ekstenziju treba biti
    "XML File" sa ekstenzijom "*.xml". Ove opcije trebaju biti realizirane pomoću metoda doOpen i doSave koje
    primaju File, a tipa su void. Format datoteke treba biti kao u sljedećem primjeru:
    <?xml version="1.0"> <biblioteka>    <knjiga brojStranica="500">

      <autor>Meša Selimović</autor>       <naslov>Tvrđava</naslov>       <isbn>abcd</isbn>
       <datum>20. 3. 1930</datum>    </knjiga>    <knjiga>...</knjiga> </biblioteka>
       (Svi elementi i atributi su obavezni.) U slučaju da format datoteke nije kao iznad, doOpen metoda treba
       prikazati dijalog (Alert tipa Error) sa naslovom "Pogrešan format datoteke" i prikladnim tekstom, a
       postojeći sadržaj biblioteke treba ostati neizmijenjen. */

    public static void doSave(File f) {
        XMLEncoder encoder=null;
        try{
            encoder=new XMLEncoder(new BufferedOutputStream(new FileOutputStream("biblioteka.xml")));
        }catch(FileNotFoundException fileNotFound){
            System.out.println("nije mogao kreirati xml datoteku");
        }
        encoder.writeObject(f);
        encoder.close();

    }

    public static void doOpen(File f) {// čitanje iz datoteke
        //File fx = new File();
        ArrayList<Knjiga> knjige = new ArrayList<Knjiga>();
        Document xmldoc = null;
        try {
            DocumentBuilder docReader
                    = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            xmldoc = docReader.parse(new File(GlavnaController.class.getResource("biblioteka.xml").getFile()));
            NodeList nList = xmldoc.getElementsByTagName("biblioteka");


            for (int i = 0; i < nList.getLength(); i++) {
                Node node = nList.item(i);
                if (node instanceof Element) {
                    Element e = (Element) node;
                    String naziv = e.getElementsByTagName("naziv").item(0).getTextContent();
                    int brojStranica = Integer.parseInt(e.getElementsByTagName("brojStranica").item(0).getTextContent());

                    String stranica = e.getElementsByTagName("brojStranica").item(0).
                            getAttributes().item(0).getTextContent();

                    Element autor = (Element) e.getElementsByTagName("autor").item(0);
                    String autorNaziv = autor.getElementsByTagName("autor").item(0).getTextContent();
                    Element naslov = (Element) e.getElementsByTagName("naslov").item(0);
                    String naslovNaziv = naslov.getElementsByTagName("naslov").item(0).getTextContent();
                    Element isbn = (Element) e.getElementsByTagName("isbn").item(0);
                    String isbnNaziv = isbn.getElementsByTagName("isbn").item(0).getTextContent();

                    //Knjiga k = new Knjiga(naslov, autor, isbn, brojStranica);
                    //knjige.add(k);
                }

            }

        } catch (Exception e) {
            System.out.println("nema datoteke");
        }

    }

    public GlavnaController(BibliotekaModel bibliotekaModel) {
        this.b = bibliotekaModel;
    }

    @FXML
    public void initialize() {
        menuBar.setFocusTraversable(true);
    }

    public void handleAboutAction(ActionEvent actionEvent) {
        System.out.println("About");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("aboutforma.fxml"));
            /*
             * if "fx:controller" is not set in fxml
             * fxmlLoader.setController(NewWindowController);
             */
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage stage = new Stage();
            stage.setTitle("New Window");
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void OpenAction(ActionEvent actionEvent) {
    }

    public void SaveAction(ActionEvent actionEvent) {
    }

    public void PrintAction(ActionEvent actionEvent) {
        BibliotekaModel b = new BibliotekaModel();
        System.out.println("Knjige su:" + b.dajKnjige());
    }

    public void ExitAction(ActionEvent actionEvent) {
    }

    public void AddAction(ActionEvent actionEvent) {
        System.out.println("Add");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("addforma.fxml"));
            /*
             * if "fx:controller" is not set in fxml
             * fxmlLoader.setController(NewWindowController);
             */
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage stage = new Stage();
            stage.setTitle("New Window");
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void ChangeAction(ActionEvent actionEvent) {
        System.out.println("Change");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("changeforma.fxml"));
            /*
             * if "fx:controller" is not set in fxml
             * fxmlLoader.setController(NewWindowController);
             */
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage stage = new Stage();
            stage.setTitle("New Window");
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void DeleteAction(ActionEvent actionEvent) {
    }

    public void tbAdd(ActionEvent actionEvent) {
    }

    public void tbDelete(ActionEvent actionEvent) {
      /*  Action response = Dialogs.create()
                .owner(stage)
                .title("Confirm Dialog with Custom Actions")
                .masthead("Look, a Confirm Dialog with Custom Actions")
                .message("Are you ok with this?")
                .actions(Dialog.Actions.OK, Dialog.Actions.CANCEL)
                .showConfirm();

        if (response == Dialog.Actions.OK) {
            // ... user chose OK
        } else {
            // ... user chose CANCEL, or closed the dialog
        }*/
    }

    public void tbChange(ActionEvent actionEvent) {
    }

    public void doOpen(ActionEvent actionEvent) {
    }


    /*○ Pošto još uvijek nismo obradili rad sa štampačem,
       opcija Print treba ispisati na standardni izlaz spisak knjiga u sljedećem obliku: Knjige su: Autor, Naslov,
       ISBN, 123, 01. 01. 1980 Autor, Naslov, ISBN, 123, 01. 01. 1980 ... Ova opcija treba biti implementirana
       pomoću metode ​dajKnjige​​ u klasi BibliotekaModel koja treba vratiti string istog oblika (bez poruke "Knjige su:"),
        a zatim metoda ​ispisiKnjige​​ treba pozvati metodu dajKnjige te ispisati "Knjige su:\n" i string koji je vratila
        spomenuta metoda.*/


    /* ○ Meni opcije Add, Change i Delete trebaju obavljati dodavanje, promjenu i brisanje knjige
        respektivno, što će biti objašnjeno ispod. */


    /*○ Opcija About treba prikazati novi prozor sa sljedećim
        informacijama: naziv programa, naziv autora i neka interesantna sličica (koju ćete prikazati koristeći
        ImageView kontrolu). ○ Sve opcije menija trebaju imati mnemonike za navigaciju tastaturom, koristeći početna
         slova, kako je objašnjeno na predavanjima. */

    /*● Traka sa alatima (toolbar) treba sadržavati ikone
         Add (fx:id ​tbAdd​​), Change (​tbChange​​) i Delete (​tbDelete​​) sa akcijama kao i odgovarajuće opcije menija.*/

    /*Statusna traka treba sadržavati labelu sa fx:id-om ​statusMsg​​ koja će u odgovarajućim situacijama ispisivati poruke:
        "Program pokrenut.", "Dodajem novu knjigu.", "Knjiga dodana.", "Knjiga nije dodana.", "Mijenjam knjigu.",
        "Knjiga izmijenjena.", "Knjiga nije izmijenjena.", "Brišem knjigu.", "Knjiga obrisana.", "Knjiga nije obrisana.",
        "Štampam knjige na standardni izlaz."*/
}
