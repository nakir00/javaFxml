package exam.ism.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import exam.ism.core.Fabrique;
import exam.ism.entities.Classe;
import exam.ism.entities.Etudiant;
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

public class InscriptionController implements Initializable {

    @FXML
    private Button btnInscrire;

    @FXML
    private ComboBox<Integer> cbAnnne;

    @FXML
    private ComboBox<Classe> cbNonClasse;

    @FXML
    private ComboBox<Classe> cbClasse;

    @FXML
    private TextField inMatricule;

    @FXML
    private TextField inNomComplet;

    @FXML
    private TextField inTuteur;

    @FXML
    private TableColumn<Etudiant, String> tblcID;

    @FXML
    private TableColumn<Classe, String> tblcLibelle;

    @FXML
    private TableColumn<Etudiant, String> tblcMatricule;

    @FXML
    private TableColumn<Etudiant, String> tblcNomComplet;

    @FXML
    private TableColumn<Etudiant, String> tblcTuteur;

    @FXML
    private TableView<Classe> tblvClasse;

    @FXML
    private TableView<Etudiant> tblvEtudiant;

    @FXML
    private Text txtMatError;

    @FXML
    private Text txtNomError;

    @FXML
    private Text txtTuteurError;

    @FXML
    private Text txtcbClassesError;

    private ObservableList obAnnee=FXCollections.observableList(Fabrique.giveMe().listerAnnee());

    private ObservableList obEtudiants=FXCollections.observableList(Fabrique.giveMe().listerEtudiant((int)(obAnnee.get(0))));

    private ObservableList obClassesAll=FXCollections.observableList(Fabrique.giveMe().ListerClasses());

    private ObservableList obClasses,obNon;


    SimpleBooleanProperty smpl=new SimpleBooleanProperty(true);

    @FXML
    void handleListerClasses(MouseEvent event) {
        Etudiant etudiant=tblvEtudiant.getSelectionModel().getSelectedItem();
        obClasses=FXCollections.observableList(Fabrique.giveMe().listerClassesEtudiant(etudiant));
        obNon= FXCollections.observableList(Fabrique.giveMe().listerNonClasseEtudiant(etudiant));
        tblvClasse.setItems(obClasses);
        cbNonClasse.setItems(obNon);

        inNomComplet.setText(etudiant.getNomComplet());
        inMatricule.setText(etudiant.getMatricule());
        inTuteur.setText(etudiant.getTuteur());

        btnInscrire.setText("REINSCRIRE");
        blockFields(true);
    }
    
    @FXML
    void handleInscrire(ActionEvent event) {
        Etudiant etudiant;
        Classe classe=cbNonClasse.getSelectionModel().getSelectedItem();
        Alert alert=new Alert(AlertType.INFORMATION);
        alert.setTitle("examen Inscription");
        if(!inMatricule.isDisabled()){
            etudiant=new Etudiant(inMatricule.getText(), inNomComplet.getText(), inTuteur.getText());
            etudiant=Fabrique.giveMe().ajouterEtudiant(etudiant);
            obEtudiants.add(etudiant);
            tblvEtudiant.setItems(obEtudiants);
         
            alert.setContentText("etudiant cree et inscrit avec success");
        
        }else{
            etudiant=tblvEtudiant.getSelectionModel().getSelectedItem();
            alert.setContentText("etudiant reinscrit avec success");
        }
        alert.showAndWait();
        Fabrique.giveMe().inscription(etudiant, classe);
        obNon.remove(classe);
        cbNonClasse.setItems(obNon);
        releaseFields();

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        txtMatError.setVisible(false);
        txtNomError.setVisible(false);
        txtTuteurError.setVisible(false);
        txtcbClassesError.setVisible(false);
        tblcID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblcMatricule.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        tblcNomComplet.setCellValueFactory(new PropertyValueFactory<>("nomComplet"));
        tblcTuteur.setCellValueFactory(new PropertyValueFactory<>("tuteur"));
        tblvEtudiant.setItems(obEtudiants);

        tblcLibelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        tblvClasse.setItems(obClasses);
        cbNonClasse.setItems(obClassesAll);

        cbClasse.setItems(obClassesAll);
        cbAnnne.setItems(obAnnee);
        cbAnnne.setValue((int)(obAnnee.get(0)));

        cbNonClasse.valueProperty().addListener((obv,old,newV)->{
            if(newV==null){
                txtcbClassesError.setVisible(true);
                smpl.set(true);
            }else{
                smpl.set((inMatricule.getText().isEmpty())||(inNomComplet.getText().isEmpty())||(inTuteur.getText().isEmpty()));
                txtcbClassesError.setVisible(false); 
            }
        });

        inMatricule.textProperty().addListener((obv,old,newV)->{
            if(newV.isEmpty()){
                txtMatError.setVisible(true);
                smpl.set(true);
            }else{
                smpl.set((cbNonClasse.getSelectionModel().isEmpty())||(inNomComplet.getText().isEmpty())||(inTuteur.getText().isEmpty()));
                txtMatError.setVisible(false); 
            }
        });

        inNomComplet.textProperty().addListener((obv,old,newV)->{
            if(newV.isEmpty()){
                txtNomError.setVisible(true);
                smpl.set(true);
            }else{
                smpl.set((cbNonClasse.getSelectionModel().isEmpty())||(inMatricule.getText().isEmpty())||(inTuteur.getText().isEmpty()));
                txtNomError.setVisible(false); 
            }
        });

        inTuteur.textProperty().addListener((obv,old,newV)->{
            if(newV.isEmpty()){
                txtTuteurError.setVisible(true);
                smpl.set(true);
            }else{
                smpl.set((cbNonClasse.getSelectionModel().isEmpty())||(inMatricule.getText().isEmpty())||(inNomComplet.getText().isEmpty()));
                txtTuteurError.setVisible(false); 
            }
        });

        btnInscrire.disableProperty().bind(smpl);
    }

    @FXML
    void handleInRelease(MouseEvent event) {
        
        blockFields(false);
        releaseFields();
        cbNonClasse.setItems(obClassesAll);

        btnInscrire.setText("INSCRIRE");
        smpl.set(true);

    }

    void blockFields(Boolean m){
        inNomComplet.setDisable(m);
        inMatricule.setDisable(m);
        inTuteur.setDisable(m);
        
    }

    void releaseFields(){
        inNomComplet.clear();
        inMatricule.clear();
        inTuteur.clear();
    }

    @FXML
    void handleFiltrerByAnne(ActionEvent event) {
        int annee=cbAnnne.getSelectionModel().getSelectedItem();
        obEtudiants=FXCollections.observableList(Fabrique.giveMe().listerEtudiant(annee));
        tblvEtudiant.setItems(obEtudiants);
    }

    @FXML
    void handleFiltrerByClasse(ActionEvent event) {
        int annee=cbAnnne.getSelectionModel().getSelectedItem();
        Classe classe=cbClasse.getSelectionModel().getSelectedItem();
        obEtudiants=FXCollections.observableList(Fabrique.giveMe().listerEtudiant(annee,classe));
        tblvEtudiant.setItems(obEtudiants);
    }

}
