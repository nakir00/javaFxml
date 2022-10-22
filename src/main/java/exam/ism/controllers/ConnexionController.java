package exam.ism.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import exam.ism.App;
import exam.ism.core.Fabrique;
import exam.ism.entities.Role;
import exam.ism.entities.User;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class ConnexionController implements Initializable {

    @FXML
    private Button btnSeConnecter;
    @FXML
    public Text txtError;
    @FXML
    TextField inLogin;
    @FXML
    PasswordField inPwd;
    @FXML
    private Text txtLoginError;
    @FXML
    private Text txtPwdError;

    SimpleBooleanProperty smpl=new SimpleBooleanProperty(false);

    public  static  User user;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        txtError.setVisible(false);
        txtLoginError.setVisible(false);
        txtPwdError.setVisible(false);
        inLogin.textProperty().addListener((obv,old,newV)->{
            if(newV.isEmpty()){
                txtLoginError.setVisible(true);
            }else{
                  smpl.set(inPwd.getText().isEmpty());
                  txtLoginError.setVisible(false); 
            }
        });

        inPwd.textProperty().addListener((obv,old,newV)->{
            if(newV.isEmpty()){
                txtPwdError.setVisible(true);
            }else{
                  smpl.set(inLogin.getText().isEmpty());
                  txtPwdError.setVisible(false); 
            }
        });
        
           btnSeConnecter.disableProperty().bind(smpl);
    }

    public void handleAnnuler(){
        inLogin.setText("");
        inPwd.setText("");
    }

    public void handleConnexion() throws IOException{
        String login=inLogin.getText().trim();
        String pwd= inPwd.getText().trim();
        user=Fabrique.giveMe().seConnecter(login, pwd);
        if(user==null){
            txtError.setVisible(true);
        }else{
            App.setRoot("home");
            txtError.setVisible(false);
        }
    }
}
