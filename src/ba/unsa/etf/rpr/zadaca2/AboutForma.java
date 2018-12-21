package ba.unsa.etf.rpr.zadaca2;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;

public class AboutForma {
    @FXML
    public ImageView imgview;
    public TextField knjigaNaslov;
    public TextField knjigaAutor;

    public AboutForma() {}

    @FXML
    public void initialize() {
        String imageFile = "src/ba/unsa/etf/rpr/zadaca2/slika.png";

        try {
            FileInputStream input = new FileInputStream(imageFile);
            Image image = new Image(input);
            imgview.setImage(image);
            imgview.setVisible(true);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
