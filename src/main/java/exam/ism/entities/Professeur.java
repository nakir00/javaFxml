package exam.ism.entities;

import java.util.List;

public class Professeur {
    private int id;
    private int nci;
    private String nomComplet;
    private String grade;

    private List<Classe> classes;


    public Professeur(int nci, String nomComplet, String grade) {
        this.nci = nci;
        this.nomComplet = nomComplet;
        this.grade = grade;
    }
    public Professeur(int id, int nci, String nomComplet, String grade) {
        this.id = id;
        this.nci = nci;
        this.nomComplet = nomComplet;
        this.grade = grade;
    }
    public List<Classe> addClasse(Classe classe){
        classes.add(classe);
        return classes;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getNci() {
        return nci;
    }
    public void setNci(int nci) {
        this.nci = nci;
    }
    public String getNomComplet() {
        return nomComplet;
    }
    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
    }
    public String getGrade() {
        return grade;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }
    

}
