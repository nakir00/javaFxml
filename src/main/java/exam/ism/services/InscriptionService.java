package exam.ism.services;

import java.util.List;
import exam.ism.entities.Classe;
import exam.ism.entities.Etudiant;
import exam.ism.entities.Professeur;
import exam.ism.entities.User;
import exam.ism.repositories.IClasseRepository;
import exam.ism.repositories.IEtudiantRepository;
import exam.ism.repositories.IProfesseurRepository;
import exam.ism.repositories.IUserRepository;

public class InscriptionService implements IInscriptionService {
    IClasseRepository classeRepository;
    IEtudiantRepository etudiantRepository;
    IProfesseurRepository professeurRepository;
    IUserRepository userRepository;
    
    public InscriptionService(IClasseRepository classeRepository, IEtudiantRepository etudiantRepository,
            IProfesseurRepository professeurRepository,IUserRepository userRepository) {
        this.classeRepository = classeRepository;
        this.etudiantRepository = etudiantRepository;
        this.professeurRepository = professeurRepository;
        this.userRepository=userRepository;
    }

    @Override
    public Classe creerClasse(Classe classe) {
        // TODO Auto-generated method stub
        return classeRepository.addClasse(classe);
    }

    @Override
    public List<Classe> ListerClasses() {
        // TODO Auto-generated method stub
        return classeRepository.findClasses();
    }

    @Override
    public Professeur ajouterProfesseur(Professeur professeur) {
        // TODO Auto-generated method stub
        return professeurRepository.addProfesseur(professeur);
    }

    @Override
    public Classe affecterClasseProfesseur(Classe classe, Professeur professeur) {
        // TODO Auto-generated method stub
        return professeurRepository.affecterClasse(classe, professeur);
    }

    @Override
    public List<Professeur> listerProfesseurs() {
        // TODO Auto-generated method stub
        return professeurRepository.findProfesseurs();
    }

    @Override
    public List<Classe> filtrerClassesProfesseur(Professeur professeur) {
        // TODO Auto-generated method stub
        return professeurRepository.classesDuProf(professeur);
    }

    @Override
    public Etudiant inscription(Etudiant etudiant, Classe classe) {
        // TODO Auto-generated method stub
        return etudiantRepository.inscrireEtudiant(etudiant,classe);
    }

    @Override
    public List<Etudiant> listerEtudiant(int annee) {
        // TODO Auto-generated method stub
        return etudiantRepository.filterByAnne(annee);
    }

    @Override
    public User seConnecter(String login, String Pwd) {
        // TODO Auto-generated method stub
        return userRepository.findUser(login, Pwd);
    }

    @Override
    public List<Classe> classesNonAffecteAuProf(Professeur professeur) {
        // TODO Auto-generated method stub
        return professeurRepository.classesNonAffecteAuProf(professeur);
    }

    @Override
    public List<Classe> listerClassesEtudiant(Etudiant etudiant) {
        // TODO Auto-generated method stub
        return etudiantRepository.listerclasseEtudiant(etudiant);
    }

    @Override
    public List<Classe> listerNonClasseEtudiant(Etudiant etudiant) {
        // TODO Auto-generated method stub
        return etudiantRepository.listerNonClasseEtudiant(etudiant);
    }

    @Override
    public List<Integer> listerAnnee() {
        // TODO Auto-generated method stub
        return etudiantRepository.listerAnne();
    }

    @Override
    public Etudiant ajouterEtudiant(Etudiant etudiant) {
        // TODO Auto-generated method stub
        return etudiantRepository.addEtudiant(etudiant);
    }

    @Override
    public List<Etudiant> listerEtudiant(int annee, Classe classe) {
        // TODO Auto-generated method stub
        return etudiantRepository.filterByClasse(annee, classe);
    }
    
}
