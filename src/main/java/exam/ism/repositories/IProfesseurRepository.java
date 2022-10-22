package exam.ism.repositories;

import java.util.List;

import exam.ism.entities.Classe;
import exam.ism.entities.Professeur;

public interface IProfesseurRepository {
    public Professeur addProfesseur(Professeur professeur);
    public List<Professeur> findProfesseurs();
    public Professeur findProfesseur(String nomComplet);
    public List<Classe> classesDuProf(Professeur professeur);
    public List<Classe> classesNonAffecteAuProf(Professeur professeur);
    public Classe affecterClasse(Classe classe, Professeur professeur);
}
