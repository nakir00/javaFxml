package exam.ism.repositories.bd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exam.ism.core.MysqlDb;
import exam.ism.entities.Role;
import exam.ism.entities.User;
import exam.ism.repositories.IUserRepository;

public class UserRepository extends MysqlDb implements IUserRepository {

    private final String SQL_AUTH="SELECT * FROM `user` WHERE `login` LIKE ? AND `pwd` LIKE ? ";

    PreparedStatement ps;
    @Override
    public User findUser(String login, String mdp) {
        User user= null;
        this.ouvrirConnexionBD();
        try {
            ps=conn.prepareStatement(SQL_AUTH);
            ps.setString(1, login);
            ps.setString(2, mdp);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                 user = new User(rs.getInt("id"), 
                 (rs.getString("role").compareTo("RP")==0?Role.RP:Role.AC),
                  rs.getString("login"),
                  rs.getString("pwd"),
                  rs.getString("nom_complet"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.fermerConnexionBD();
        return user;
    }

    
    
}
