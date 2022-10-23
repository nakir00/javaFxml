package exam.ism.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import exam.ism.core.Fabrique;
import exam.ism.entities.Classe;
import exam.ism.entities.Professeur;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class ProfesseurController  implements Initializable{

    @FXML
    private Button btnAffecter;

    @FXML
    private Button btnAjouter;

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

    @FXML
    private Text txtErrorClasse;

    @FXML
    private Text txtErrorGrade;

    @FXML
    private Text txtErrorNci;

    @FXML
    private Text txtErrorNom;

    @FXML
    private Text txtErrorProf;

    private ObservableList obProfs= FXCollections.observableList(Fabrique.giveMe().listerProfesseurs());

    private ObservableList obClasses, notAffected;

    private Professeur currentProf;

    SimpleBooleanProperty smpl=new SimpleBooleanProperty(true);
    SimpleBooleanProperty spl=new SimpleBooleanProperty(true);

    

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
        txtErrorNci.setVisible(false);
        txtErrorNom.setVisible(false);
        txtErrorGrade.setVisible(false);
        txtErrorClasse.setVisible(false);
        // TODO Auto-generated method stub
        tblcId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblcNci.setCellValueFactory(new PropertyValueFactory<>("nci"));
        tblcNom.setCellValueFactory(new PropertyValueFactory<>("nomComplet"));
        tblcGrade.setCellValueFactory(new PropertyValueFactory<>("grade"));
        tblvProf.setItems(obProfs);
        tblcLibelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        tblvClasses.setItems(obClasses);
        cbNon.setItems(notAffected);

        inNci.textProperty().addListener((obv,old,newV)->{
            if(newV.isEmpty()){
                txtErrorNci.setVisible(true);
                smpl.set(true);
                
            }else{
                smpl.set((inNom.getText().isEmpty())||(inGrade.getText().isEmpty()));
                txtErrorNci.setVisible(false); 
            }
        });

        inNom.textProperty().addListener((obv,old,newV)->{
            if(newV.isEmpty()){
                txtErrorNom.setVisible(true);
                smpl.set(true);
            }else{
                smpl.set((inNci.getText().isEmpty())||(inGrade.getText().isEmpty()));
                txtErrorNom.setVisible(false); 
            }
        });
        inGrade.textProperty().addListener((obv,old,newV)->{
            if(newV.isEmpty()){
                txtErrorGrade.setVisible(true);
                smpl.set(true);
            }else{
                smpl.set((inNom.getText().isEmpty())||(inNci.getText().isEmpty()));
                txtErrorGrade.setVisible(false); 
            }
        });

        cbNon.valueProperty().addListener((obv,old,newV)->{
            if(newV==null){
                txtErrorClasse.setVisible(true);
            }else{
                spl.set((tblvProf.getSelectionModel().isEmpty()));
                txtErrorClasse.setVisible(false); 
            }
        });
        
        inSelectedProf.textProperty().addListener((obv,old,newV)->{
            if(newV.isEmpty()){
                txtErrorProf.setVisible(true);
                smpl.set(true);
            }else{
                spl.set((cbNon.getSelectionModel().isEmpty()));
                txtErrorProf.setVisible(false); 
            }
        });

        btnAjouter.disableProperty().bind(smpl);
        btnAffecter.disableProperty().bind(spl);
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
