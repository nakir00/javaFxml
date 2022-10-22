package exam.ism.repositories;

import java.util.List;

import exam.ism.entities.Classe;
import exam.ism.entities.Etudiant;

public interface IEtudiantRepository {
    public List<Etudiant> findEtudiants();
    public Etudiant findEtudiant(String nomComplet);
    public Etudiant addEtudiant(Etudiant etudiant);
    public Etudiant inscrireEtudiant(Etudiant etudiant,Classe classe);
    public List<Etudiant> filterByAnne(int annee);
    public List<Etudiant> filterByClasse(int annee, Classe classe);
    public List<Classe> listerclasseEtudiant(Etudiant etudiant);
    public List<Classe> listerNonClasseEtudiant(Etudiant etudiant);
    public List<Integer> listerAnne();
}
