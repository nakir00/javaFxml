package exam.ism.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import exam.ism.core.Fabrique;
import exam.ism.entities.Classe;
import exam.ism.entities.Professeur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class ProfesseurController  implements Initializable{

    @FXML
    private ComboBox<Classe> cbNon;

    @FXML
    private TextField inGrade;

    @FXML
    private TextField inNci;

    @FXML
    private TextField inNom;

    @FXML
    private TextField inSelectedProf;


    @FXML
    private TableColumn<Professeur, String> tblcGrade;

    @FXML
    private TableColumn<Professeur, Integer> tblcId;

    @FXML
    private TableColumn<Classe, String> tblcLibelle;

    @FXML
    private TableColumn<Professeur, String> tblcNci;

    @FXML
    private TableColumn<Professeur, String> tblcNom;

    @FXML
    private TableView<Classe> tblvClasses;

    @FXML
    private TableView<Professeur> tblvProf;

    private ObservableList obProfs= FXCollections.observableList(Fabrique.giveMe().listerProfesseurs());

    private ObservableList obClasses, notAffected;

    private Professeur currentProf;



    
    //ObservableList selectedCells = tableView.getSelectionModel().getSelectedCells();

    

    @FXML
    void handleAjouterProf(ActionEvent event) {
        int nci=Integer.parseInt(inNci.getText());
        String nomComplet=inNom.getText();
        String grade= inGrade.getText();
        
        Professeur professeur=Fabrique.giveMe().ajouterProfesseur(new Professeur(nci, nomComplet, grade));
        obProfs.add(professeur);
        Alert alert=new Alert(AlertType.INFORMATION);
         alert.setTitle("examen Inscription");
         alert.setContentText("professeur cree avec success");
         alert.showAndWait();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        tblcId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblcNci.setCellValueFactory(new PropertyValueFactory<>("nci"));
        tblcNom.setCellValueFactory(new PropertyValueFactory<>("nomComplet"));
        tblcGrade.setCellValueFactory(new PropertyValueFactory<>("grade"));
        tblvProf.setItems(obProfs);
        tblcLibelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        tblvClasses.setItems(obClasses);
        cbNon.setItems(notAffected);
    }

    @FXML
    void handleListerClasses(MouseEvent event) {
        currentProf= tblvProf.getSelectionModel().getSelectedItem();
        loadClasses();
        inSelectedProf.setText(currentProf.getNomComplet());
    }

    @FXML
    void handleAffecterClasse(ActionEvent event) {
        currentProf= tblvProf.getSelectionModel().getSelectedItem();
        System.out.println(currentProf);
        Classe classe=cbNon.getSelectionModel().getSelectedItem();
        Fabrique.giveMe().affecterClasseProfesseur(classe, currentProf);
        loadClasses();
        notAffected.remove(classe);
        cbNon.setItems(notAffected);
        Alert alert=new Alert(AlertType.INFORMATION);
         alert.setTitle("examen Inscription");
         alert.setContentText("classe affectée avec success \n veuillez cliquer sur le professeur pour voir la mise a jour");
         alert.showAndWait();
    }

    void loadClasses(){
        obClasses=FXCollections.observableList(Fabrique.giveMe().filtrerClassesProfesseur(currentProf));
        notAffected= FXCollections.observableList(Fabrique.giveMe().classesNonAffecteAuProf(currentProf));
        tblvClasses.setItems(obClasses);
        cbNon.setItems(notAffected);
    }
}
