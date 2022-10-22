package exam.ism.entities;

import java.util.Date;
import java.util.List;


public class Classe {
    private int id;
    private String libelle;
    private List<Professeur> professeurs;
    private List<Etudiant> etudiants;
    private boolean enCours;
    private Date date;
    
    
   
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Classe(String libelle) {
        this.libelle = libelle;
        this.enCours = true;
    }

    public Classe(int id, String libelle, boolean enCours, Date date) {
        this.id = id;
        this.libelle = libelle;
        this.enCours = enCours;
        this.date = date;
    }

    public  List<Etudiant> addEtudiants(Etudiant etudiant){
        etudiants.add(etudiant);
        return etudiants;
    }
    public  List<Professeur> addProfesseurs(Professeur professeur){
        professeurs.add(professeur);
        return professeurs;
    }
    public boolean isEnCours() {
        return enCours;
    }
    public void setEnCours(boolean enCours) {
        this.enCours = enCours;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getLibelle() {
        return libelle;
    }
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public String toString() {
        return libelle;
    }
    
}
