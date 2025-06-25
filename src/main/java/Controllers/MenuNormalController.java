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

public class MenuNormalController {

    @FXML
    private Button btnBuscarPlano;

    @FXML
    protected void onAdicionarAoCarrinhoClick() throws SQLException, IOException {
        Parent root = FXMLLoader.load(MenuNormalController.class.getResource("/Views/AdicionarCarrinho.fxml"));
        Scene novaCena = new Scene(root);
        Stage stage = (Stage) btnBuscarPlano.getScene().getWindow();
        stage.setScene(novaCena);
        stage.setTitle("Adicionar ao Carrinho");
        stage.show();
    }

    @FXML
    protected void onBuscarPlanoClick() throws SQLException, IOException {
        Parent root = FXMLLoader.load(MenuNormalController.class.getResource("/Views/BuscarPlano.fxml"));
        Scene novaCena = new Scene(root);
        Stage stage = (Stage) btnBuscarPlano.getScene().getWindow();
        stage.setScene(novaCena);
        stage.setTitle("Buscar Plano");
        stage.show();
    }

    @FXML
    protected void onRemoverDoCarrinhoClick() throws SQLException, IOException {
        Parent root = FXMLLoader.load(MenuNormalController.class.getResource("/Views/RemoverCarrinho.fxml"));
        Scene novaCena = new Scene(root);
        Stage stage = (Stage) btnBuscarPlano.getScene().getWindow();
        stage.setScene(novaCena);
        stage.setTitle("Remover do carrinho");
        stage.show();
    }

    @FXML
    protected void onConfirmarCompraClick() throws SQLException, IOException {
        Parent root = FXMLLoader.load(MenuNormalController.class.getResource("/Views/ConfirmarCompra.fxml"));
        Scene novaCena = new Scene(root);
        Stage stage = (Stage) btnBuscarPlano.getScene().getWindow();
        stage.setScene(novaCena);
        stage.setTitle("Confirmar Compra");
        stage.show();
    }

    @FXML
    private void onLogoutClick(javafx.event.ActionEvent event) throws Exception {
        Parent login = FXMLLoader.load(getClass().getResource("/Views/hello-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(login));
    }
}