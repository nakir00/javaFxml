package exam.ism.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import exam.ism.App;
import exam.ism.core.Fabrique;
import exam.ism.entities.Role;
import exam.ism.entities.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class ConnexionController implements Initializable {

    @FXML
    public Text txtError;
    @FXML
    TextField inLogin;
    @FXML
    PasswordField inPwd;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        txtError.setVisible(false);
    }

    public void handleAnnuler(){
        inLogin.setText("");
        inPwd.setText("");
    }

    public void handleConnexion() throws IOException{
        String login=inLogin.getText().trim();
        String pwd= inPwd.getText().trim();
        User user=Fabrique.giveMe().seConnecter(login, pwd);
        if(user==null){
            txtError.setVisible(true);
        }else{
            if (user.getRole()==Role.RP) {
                App.setRoot("rpHome");
            } else {
                App.setRoot("acHome");
            }
            
        }
    }
}
