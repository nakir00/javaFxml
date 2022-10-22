package exam.ism.repositories;

import java.util.List;

import exam.ism.entities.Classe;
import exam.ism.entities.Etudiant;
import exam.ism.entities.Professeur;

public interface IClasseRepository {
    public Classe addClasse(Classe classe);
    public List<Classe> findClasses();
    public Classe findclasse(String libelle);
    public List<Professeur> findProfClasse(Classe classe);
    public List<Etudiant> findetudClasse(Classe classe);

    
    
}
