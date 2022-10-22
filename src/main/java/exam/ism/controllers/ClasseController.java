package exam.ism.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import exam.ism.core.Fabrique;
import exam.ism.entities.Classe;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ClasseController implements Initializable {

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

    private ObservableList obClasses= FXCollections.observableList(Fabrique.giveMe().ListerClasses());

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        tblcId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblcLibelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        tblcDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tblvClasse.setItems(obClasses);
    }

    public void handleCreerClasse() {
        String libelle= inLibelle.getText();
        Classe classe=Fabrique.giveMe().creerClasse(new Classe(libelle));
        System.out.println(classe.getDate());
        obClasses.add(classe);
    }
}
