package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class MenuAdmController {

    @FXML
    private Button btnInserirPlano;

    @FXML
    protected void onInserirPlanoClick() throws SQLException, IOException {
        Parent root = FXMLLoader.load(MenuAdmController.class.getResource("/Views/InserirPlano.fxml"));
        Scene novaCena = new Scene(root);
        Stage stage = (Stage) btnInserirPlano.getScene().getWindow();
        stage.setScene(novaCena);
        stage.setTitle("Inserir Plano");
        stage.show();
    }

    @FXML
    protected void onBuscarPlanoClick() throws SQLException, IOException {
        Parent root = FXMLLoader.load(MenuAdmController.class.getResource("/Views/BuscarPlano.fxml"));
        Scene novaCena = new Scene(root);
        Stage stage = (Stage) btnInserirPlano.getScene().getWindow();
        stage.setScene(novaCena);
        stage.setTitle("Buscar Plano");
        stage.show();
    }

    @FXML
    protected void onRemoverPlanoClick() throws SQLException, IOException {
        Parent root = FXMLLoader.load(MenuAdmController.class.getResource("/Views/RemoverPlano.fxml"));
        Scene novaCena = new Scene(root);
        Stage stage = (Stage) btnInserirPlano.getScene().getWindow();
        stage.setScene(novaCena);
        stage.setTitle("Remover Plano");
        stage.show();
    }

    @FXML
    protected void onAtualizarPlanoClick() throws SQLException, IOException {
        Parent root = FXMLLoader.load(MenuAdmController.class.getResource("/Views/AtualizarPlano.fxml"));
        Scene novaCena = new Scene(root);
        Stage stage = (Stage) btnInserirPlano.getScene().getWindow();
        stage.setScene(novaCena);
        stage.setTitle("Atualizar Plano");
        stage.show();
    }

    @FXML
    private void onLogoutClick(javafx.event.ActionEvent event) throws Exception {
        Parent login = FXMLLoader.load(getClass().getResource("/Views/hello-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(login));
    }
}