package exam.ism.repositories.bd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

import exam.ism.core.MysqlDb;
import exam.ism.entities.Classe;
import exam.ism.entities.Etudiant;
import exam.ism.repositories.IEtudiantRepository;

public class  EtudiantRepository extends MysqlDb implements IEtudiantRepository {
    //SELECT * FROM `classe_etudiant` ce ,`classe` c WHERE c.id=ce.id_classe;
    private final String SQl_INSERT="INSERT INTO `etudiant` (`matricule`, `nom_complet`, `tuteur`) VALUES ( ?, ?, ?);";

    private final String SQL_SELECT_ALL="SELECT * FROM `etudiant`";

    private final String SQL_BY_NOM="SELECT * FROM `etudiant` WHERE `nom_complet` LIKE ? ";

    private final String SQL_SELECT_ETUD_BY_ANNEE="SELECT DISTINCT `id_etudiant`,matricule,nom_complet,tuteur FROM classe_etudiant ce ,etudiant e WHERE ce.id_etudiant=e.id_e AND annee= ? ";

    private final String SQL_BY_CLASSE="and id_classe= ?";

    private final String SQL_INSCRIRE="INSERT INTO `classe_etudiant` (`id_classe`, `id_etudiant`, `annee`, `etat`) VALUES (?, ?, YEAR(current_timestamp()), ?);;";

    private final String SQL_FIND_CLASSE_ETUDIANT="SELECT * FROM `classe_etudiant` ce ,`classe` c WHERE c.id_c=ce.id_classe AND id_etudiant= ? ";

    private final String SQL_FIND_CLASSE_NON="SELECT * FROM classe WHERE id_c NOT IN (SELECT id_c FROM classe_etudiant ce ,classe c WHERE c.id_c=ce.id_classe AND ce.id_etudiant= ? );";

    private final String SQL_SELECT_ANNEE="SELECT DISTINCT annee FROM classe_etudiant ORDER BY `classe_etudiant`.`annee` DESC;";

    PreparedStatement ps;
    @Override
    public List<Etudiant> findEtudiants() {
        List<Etudiant> etudiants =new ArrayList<>();
        this.ouvrirConnexionBD();
        try {
            ps=conn.prepareStatement(SQL_SELECT_ALL);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Etudiant etudiant= new Etudiant(rs.getInt("id_e"),
                rs.getString("matricule"),
                rs.getString("nom_complet"),
                rs.getString("tuteur"));
                etudiants.add(etudiant);
            }
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.fermerConnexionBD();
        return etudiants;
    }
    

    @Override
    public Etudiant findEtudiant(String nomComplet) {
        // TODO Auto-generated method stub
        Etudiant etudiant=null;
        this.ouvrirConnexionBD();
        try {
            ps=conn.prepareStatement(SQL_BY_NOM);
            ps.setString(1, nomComplet);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                 etudiant= new Etudiant(rs.getInt("id_e"),
                 rs.getString("matricule"),
                 rs.getString("nom_complet"),
                 rs.getString("tuteur"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.fermerConnexionBD();
        return etudiant;
    }
        

    @Override
    public Etudiant addEtudiant(Etudiant etudiant) {
        // TODO Auto-generated method stub
        this.ouvrirConnexionBD();
        try {
            ps=conn.prepareStatement(SQl_INSERT,PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, etudiant.getMatricule());
            ps.setString(2, etudiant.getNomComplet());
            ps.setString(3, etudiant.getTuteur());
            ps.executeUpdate();
            ResultSet rs= ps.getGeneratedKeys();
            if(rs.next()){
                etudiant.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.fermerConnexionBD();

        return etudiant;
    }


    @Override
    public List<Etudiant> filterByAnne(int annee) {
        // TODO Auto-generated method stub
        List<Etudiant> etudiants= new ArrayList<>();
        this.ouvrirConnexionBD();
        
        try {
            ps=conn.prepareStatement(SQL_SELECT_ETUD_BY_ANNEE);
            ps.setInt(1, annee);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Etudiant etudiant= new Etudiant(rs.getInt("id_etudiant"),
                rs.getString("matricule"),
                rs.getString("nom_complet"),
                rs.getString("tuteur"));
                etudiants.add(etudiant);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return etudiants;
    }

    @Override
    public List<Etudiant> filterByClasse(int annee, Classe classe) {
        // TODO Auto-generated method stub
        List<Etudiant> etudiants= new ArrayList<>();
        this.ouvrirConnexionBD();
        String sql=SQL_SELECT_ETUD_BY_ANNEE+SQL_BY_CLASSE;
        try {
            ps=conn.prepareStatement(sql);
            ps.setInt(1, annee);
            ps.setInt(2, classe.getId());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Etudiant etudiant= new Etudiant(rs.getInt("id_etudiant"),
                rs.getString("matricule"),
                rs.getString("nom_complet"),
                rs.getString("tuteur"));
                etudiants.add(etudiant);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return etudiants;
    }


    @Override
    public Etudiant inscrireEtudiant(Etudiant etudiant,Classe classe) {
        // TODO Auto-generated method stub
        this.ouvrirConnexionBD();
        try {
            ps=conn.prepareStatement(SQL_INSCRIRE,PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, classe.getId());
            ps.setInt(2, etudiant.getId());
            ps.setBoolean(3, true);
            ps.executeUpdate();
            ResultSet rs= ps.getGeneratedKeys();
            if(rs.next()){
                etudiant.setId(rs.getInt("id_e"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.fermerConnexionBD();

        return etudiant;
    }


    @Override
    public List<Classe> listerclasseEtudiant(Etudiant etudiant) {
        // TODO Auto-generated method stub
        List<Classe> classes= new ArrayList<>();
         this.ouvrirConnexionBD();
         try {
             ps=conn.prepareStatement(SQL_FIND_CLASSE_ETUDIANT);
             ps.setInt(1, etudiant.getId());
             ResultSet rs = ps.executeQuery();
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
         return classes;
    }


    @Override
    public List<Classe> listerNonClasseEtudiant(Etudiant etudiant) {
        // TODO Auto-generated method stub
        List<Classe> classes = new ArrayList<>();
        this.ouvrirConnexionBD();
        try {
            ps=conn.prepareStatement(SQL_FIND_CLASSE_NON);
            ps.setInt(1, etudiant.getId());
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
    public List<Integer> listerAnne() {
        List<Integer> annees = new ArrayList<>();
        this.ouvrirConnexionBD();
        try {
            ps=conn.prepareStatement(SQL_SELECT_ANNEE);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                annees.add(rs.getInt("annee"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.fermerConnexionBD();
        return annees;
    }
    
}
