package exam.ism.core;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MysqlDb implements IDatabase {

    protected Connection conn;
    protected PreparedStatement pstmt;
    @Override
    public void ouvrirConnexionBD() {
        // TODO Auto-generated method stub
        try {
            //1- charger le driver
            Class.forName("com.mysql.cj.jdbc.Driver");
             conn=     DriverManager.getConnection("jdbc:mysql://localhost:3306/ges_classe_java", "root", "");
            //3- executer la requete
            
        } catch(SQLException e){
            e.printStackTrace();
        } 
        catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void fermerConnexionBD() {
        // TODO Auto-generated method stub
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
    }

    
}
