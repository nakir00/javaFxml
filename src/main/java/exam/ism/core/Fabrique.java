package exam.ism.core;

import exam.ism.repositories.IClasseRepository;
import exam.ism.repositories.IEtudiantRepository;
import exam.ism.repositories.IProfesseurRepository;
import exam.ism.repositories.IUserRepository;
import exam.ism.repositories.bd.ClasseRepository;
import exam.ism.repositories.bd.EtudiantRepository;
import exam.ism.repositories.bd.ProfesseurRepository;
import exam.ism.repositories.bd.UserRepository;
import exam.ism.services.IInscriptionService;
import exam.ism.services.InscriptionService;

public class Fabrique {
    public static IInscriptionService giveMe(){

        IClasseRepository classeRepository=new ClasseRepository();
        IEtudiantRepository etudiantRepository=new EtudiantRepository();
        IProfesseurRepository professeurRepository=new ProfesseurRepository();
        IUserRepository userRepository=new UserRepository();
        return new InscriptionService(classeRepository,etudiantRepository, professeurRepository,userRepository);
    }
}
