package exam.ism.repositories.bd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exam.ism.core.MysqlDb;
import exam.ism.entities.Classe;
import exam.ism.entities.Professeur;
import exam.ism.repositories.IProfesseurRepository;

public class ProfesseurRepository extends MysqlDb implements IProfesseurRepository{

    private final String SQl_INSERT="INSERT INTO `professeur` (`nci`, `nom_complet`, `grade`) VALUES (?,?,?);";

    private final String SQL_SELECT_ALL="SELECT * FROM `professeur`";

    private final String SQL_BY_NOM="SELECT * FROM `professeur` WHERE `nom_complet` LIKE ? ";

    private final String SQL_AFFECT_CLASSE="INSERT INTO `classe_professeur` (`id_classe`, `id_professeur`) VALUES (?, ?);";

    private final String SQL_SELECT_CLASS_PROF=" SELECT * FROM classe_professeur cp, classe c WHERE c.id_c=cp.id_classe AND cp.id_professeur= ? ;";

    private final String SQL_SELECT_NON_AFFECTE="SELECT * FROM classe WHERE id_c NOT IN (SELECT id_c FROM classe_professeur cp,classe c WHERE c.id_c=cp.id_classe AND cp.id_professeur= ? )";

    PreparedStatement ps;

    @Override
    public Professeur addProfesseur(Professeur professeur) {
        // TODO Auto-generated method stub
        this.ouvrirConnexionBD();
        try {
            ps=conn.prepareStatement(SQl_INSERT,PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, professeur.getNci());
            ps.setString(2, professeur.getNomComplet());
            ps.setString(3, professeur.getGrade());
            ps.executeUpdate();
            ResultSet rs= ps.getGeneratedKeys();
            if(rs.next()){
                System.out.println(rs.getInt(1));
                professeur.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.fermerConnexionBD();
        System.out.println(professeur.getId());
        return professeur;
    }

    @Override
    public List<Professeur> findProfesseurs() {
        // TODO Auto-generated method stub
        List<Professeur> professeurs =new ArrayList<>();
        this.ouvrirConnexionBD();
        try {
            ps=conn.prepareStatement(SQL_SELECT_ALL);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Professeur professeur= new Professeur(rs.getInt("id_p"),
                rs.getInt("nci"),
                rs.getString("nom_complet"),
                rs.getString("grade"));
                professeurs.add(professeur);
            }
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.fermerConnexionBD();
        return professeurs;
    }

    @Override
    public Professeur findProfesseur(String nomComplet) {
        // TODO Auto-generated method stub
        Professeur professeur=null;
        this.ouvrirConnexionBD();
        try {
            ps=conn.prepareStatement(SQL_BY_NOM);
            ps.setString(1, nomComplet);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                 professeur= new Professeur(rs.getInt("id"),
                 rs.getInt("nci"),
                 rs.getString("nomComplet"),
                 rs.getString("grade"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.fermerConnexionBD();
        return professeur;
    }

    @Override
    public List<Classe> classesDuProf(Professeur professeur) {
         // TODO Auto-generated method stub
        List<Classe> classes= new ArrayList<>();
        this.ouvrirConnexionBD();
        try {
            ps=conn.prepareStatement(SQL_SELECT_CLASS_PROF);
            ps.setInt(1, professeur.getId());
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Classe classe= new Classe(rs.getInt("id_c"),
                rs.getString("libelle"),
                rs.getBoolean("en_cours"),
                rs.getDate("date"));

                classes.add(classe);
            }
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.fermerConnexionBD();
        return classes;
    }

    @Override
    public Classe affecterClasse(Classe classe, Professeur professeur) {
        this.ouvrirConnexionBD();
        try {
            ps=conn.prepareStatement(SQL_AFFECT_CLASSE,PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, classe.getId());
            ps.setInt(2, professeur.getId());
            ps.executeUpdate();
            classe.addProfesseurs(professeur);
            professeur.addClasse(classe);
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.fermerConnexionBD();
        return classe;


        
    }

    @Override
    public List<Classe> classesNonAffecteAuProf(Professeur professeur) {
        // TODO Auto-generated method stub
        List<Classe> classes = new ArrayList<>();
        this.ouvrirConnexionBD();
        try {
            ps=conn.prepareStatement(SQL_SELECT_NON_AFFECTE);
            ps.setInt(1, professeur.getId());
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Classe classe= new Classe(rs.getInt("id_c"),
                rs.getString("libelle"),
                rs.getBoolean("en_cours"),
                rs.getDate("date"));

                classes.add(classe);
            }
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.fermerConnexionBD();
        return classes;
    }
    
}
