package exam.ism.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import exam.ism.App;
import exam.ism.entities.Role;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class HomeController implements Initializable {

    @FXML
    private AnchorPane ancre;

    @FXML
    private Button btnClasse;

    @FXML
    private Button btnInscription;

    @FXML
    private Button btnProfesseur;

    public void LoadView(String fxml) throws IOException {
        AnchorPane root= (AnchorPane) App.loadFXML(fxml);
        ancre.getChildren().clear();
        ancre.getChildren().add(root);
    }

    @FXML
    void handleDeconnexion(ActionEvent event) throws IOException {
        App.setRoot("connexion");
    }

    @FXML
    void handleLoadViewClasse(ActionEvent event) throws IOException {
        this.LoadView("classe");
    }

    @FXML
    void handleLoadViewInscription(ActionEvent event) throws IOException {
        this.LoadView("inscription");
    }

    @FXML
    void handleLoadViewProfesseur(ActionEvent event) throws IOException {
        this.LoadView("professeur");
    }

    private void isRP(){
        btnClasse.setDisable(false);
        btnProfesseur.setDisable(false);
        btnClasse.setVisible(true);
        btnProfesseur.setVisible(true);
      }
  
      private void isAC(){
          btnInscription.setDisable(false);
          btnInscription.setVisible(true);
      }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        try {
            if(ConnexionController.user.getRole()==Role.RP) {
                isRP();
                this.LoadView("classe");
            }else{
                isAC();
                this.LoadView("inscription");
            }
         } catch (IOException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
         }
    }

}

