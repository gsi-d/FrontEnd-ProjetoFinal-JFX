package Controllers;

import DAO.UsuarioDAO;
import Entidades.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;

public class HelloController {
    @FXML
    private TextField txtUser;
    @FXML
    private PasswordField txtPwd;

    @FXML
    protected void onLoginButtonClick() throws SQLException, IOException {
        String login = txtUser.getText();
        String senha = txtPwd.getText();

        Usuario usuario = new UsuarioDAO().Login(login, senha);

        if(usuario != null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Login efetuado com sucesso!");
            alert.show();
            if(usuario.administrador){
                Parent root = FXMLLoader.load(HelloController.class.getResource("/Views/MenuAdm.fxml"));
                Scene novaCena = new Scene(root);
                Stage stage = (Stage) txtUser.getScene().getWindow();
                stage.setScene(novaCena);
                stage.setTitle("Menu Administrativo");
                stage.show();
            }else{
                Parent root = FXMLLoader.load(HelloController.class.getResource("/Views/MenuNormal.fxml"));
                Scene novaCena = new Scene(root);
                Stage stage = (Stage) txtUser.getScene().getWindow();
                stage.setScene(novaCena);
                stage.setTitle("Menu");
                stage.show();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Login ou senha inválidos!");
            alert.show();
        }
    }
}