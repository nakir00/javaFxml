package exam.ism.services;

import java.util.List;

import exam.ism.entities.Classe;
import exam.ism.entities.Etudiant;
import exam.ism.entities.Professeur;
import exam.ism.entities.User;

public interface IInscriptionService {
    //RP
    public Classe creerClasse(Classe classe);

    public List<Classe> ListerClasses();

    public Professeur ajouterProfesseur(Professeur professeur);

    public Classe affecterClasseProfesseur(Classe classe, Professeur professeur);

    public List<Professeur> listerProfesseurs();

    public List<Classe> classesNonAffecteAuProf(Professeur professeur);

    public List<Classe> filtrerClassesProfesseur(Professeur professeur);


    //AC
    public Etudiant inscription(Etudiant etudiant,Classe classe);

    public List<Etudiant> listerEtudiant(int annee);

    public List<Etudiant> listerEtudiant(int annee,Classe classe);

    public List<Classe> listerClassesEtudiant(Etudiant etudiant);

    public List<Classe> listerNonClasseEtudiant(Etudiant etudiant);

    public List<Integer> listerAnnee();

    public Etudiant ajouterEtudiant(Etudiant etudiant);

    //sec
    public User seConnecter(String login, String Pwd);
}
