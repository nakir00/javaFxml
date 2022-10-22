package exam.ism.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import exam.ism.core.Fabrique;
import exam.ism.entities.Classe;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

public class ClasseController implements Initializable {


    @FXML
    private Button btnCreer;
    @FXML
    TableView<Classe> tblvClasse=new TableView<>();
    @FXML
    TableColumn<Classe,String> tblcId= new TableColumn<>();
    @FXML
    TableColumn<Classe,String> tblcLibelle= new TableColumn<>();
    @FXML
    TableColumn<Classe,String> tblcDate= new TableColumn<>();
    @FXML
    TextField inLibelle;
    @FXML
    private Text txtError;

    private ObservableList obClasses= FXCollections.observableList(Fabrique.giveMe().ListerClasses());

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        txtError.setVisible(false);
        // TODO Auto-generated method stub
        tblcId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblcLibelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        tblcDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tblvClasse.setItems(obClasses);
        inLibelle.textProperty().addListener((obv,old,newV)->{
            if(newV.isEmpty()){
                txtError.setVisible(true);
                btnCreer.setDisable(true);
            }else{
                btnCreer.setDisable(false);
                txtError.setVisible(false); 
            }
        });
    }

    public void handleCreerClasse() {
        String libelle= inLibelle.getText();
        Classe classe=Fabrique.giveMe().creerClasse(new Classe(libelle));
        obClasses.add(classe);
        Alert alert=new Alert(AlertType.INFORMATION);
         alert.setTitle("Examen Inscription");
         alert.setContentText("classe cree avec success");
         alert.showAndWait();
    }
}
