package ba.unsa.etf.rpr.zadaca2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("glavna.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Biblioteka");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
        // test pusha na repo
    }


    public static void main(String[] args) {
        launch(args);
    }
}
