package exam.ism.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import exam.ism.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;


public class RPHomeController implements Initializable{

    @FXML
    AnchorPane ancre;

    public void LoadView(String fxml) throws IOException {
        AnchorPane root= (AnchorPane) App.loadFXML(fxml);
        ancre.getChildren().clear();
        ancre.getChildren().add(root);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        try {
            this.LoadView("classe");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void handleDeconnexion() throws IOException {
        App.setRoot("connexion");
    }

    public void handleLoadViewClasse() throws IOException {
        this.LoadView("classe");
    }

    public void handleLoadViewProfesseur() throws IOException {
        this.LoadView("professeur");
    }
    
}