package exam.ism.entities;

import java.util.Date;


public class Inscription {

    private Etudiant etudiant;
    private Classe classe;
    private Date date;
    
   


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Inscription(Etudiant etudiant, Classe classe) {
        this.etudiant = etudiant;
        this.classe = classe;
        this.date=new Date();
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

}
