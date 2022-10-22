package exam.ism.repositories.bd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exam.ism.core.MysqlDb;
import exam.ism.entities.Classe;
import exam.ism.entities.Etudiant;
import exam.ism.entities.Professeur;
import exam.ism.repositories.IClasseRepository;

public class ClasseRepository extends MysqlDb implements IClasseRepository{
    private final String SQl_INSERT="INSERT INTO `classe` (`libelle`, `en_cours`) VALUES ( ?, ? )";

    private final String SQL_SELECT_ALL="SELECT * FROM `classe` ";

    private final String SQL_BY_LIBELLE="SELECT * FROM `classe` WHERE `libelle`LIKE ? ";

    private final String SQL_SELECT_PROF_CLASSE=" SELECT * FROM classe_professeur cp, professeur p WHERE p.id_p=cp.id_professeur AND  cp.id_classe= ? ;";

    private final String SQL_SELECT_ETUD_CLASSE=" SELECT * FROM classe_etudiant ce, etudiant e WHERE e.id_e=ce.id_etudiant AND ce.id_classe = ? ;";

    PreparedStatement ps;

    @Override
    public Classe addClasse(Classe classe) {
        // TODO Auto-generated method stub
        this.ouvrirConnexionBD();
        try {
            ps=conn.prepareStatement(SQl_INSERT,PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, classe.getLibelle());
            ps.setBoolean(2, true);
            ps.executeUpdate();
            ResultSet rs= ps.getGeneratedKeys();
            if(rs.next()){
                classe.setId(rs.getInt("id_c"));
                classe.setDate(rs.getDate("date"));
                classe.setEnCours(rs.getBoolean("en_cours"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.fermerConnexionBD();
        return classe;
    }

    @Override
    public List<Classe> findClasses() {
        // TODO Auto-generated method stub

        List<Classe> classes =new ArrayList<>();
        this.ouvrirConnexionBD();
        try {
            ps=conn.prepareStatement(SQL_SELECT_ALL);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                 
                classes.add(new Classe(rs.getInt("id_c"),
                rs.getString("libelle"),
                rs.getBoolean("en_cours"),
                 rs.getDate("date")));
            }
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.fermerConnexionBD();
        return classes;
    }

    @Override
    public Classe findclasse(String libelle) {
        // TODO Auto-generated method stub
        Classe classe=null;
        this.ouvrirConnexionBD();
        try {
            ps=conn.prepareStatement(SQL_BY_LIBELLE);
            ps.setString(1, libelle);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                 classe= new Classe(rs.getInt("id"),
                    rs.getString("libelle"),
                    rs.getBoolean("en_cours"),
                    rs.getDate("date"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.fermerConnexionBD();
        return classe;
    }

    @Override
    public List<Professeur> findProfClasse(Classe classe) {
        // TODO Auto-generated method stub
        List<Professeur> professeurs =new ArrayList<>();
        this.ouvrirConnexionBD();
        try {
            ps=conn.prepareStatement(SQL_SELECT_PROF_CLASSE);
            ps.setInt(1, classe.getId());
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
    public List<Etudiant> findetudClasse(Classe classe) {
        // TODO Auto-generated method stub
        List<Etudiant> etudiants =new ArrayList<>();
        this.ouvrirConnexionBD();
        try {
            ps=conn.prepareStatement(SQL_SELECT_ETUD_CLASSE);
            ps.setInt(1, classe.getId());
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
}
