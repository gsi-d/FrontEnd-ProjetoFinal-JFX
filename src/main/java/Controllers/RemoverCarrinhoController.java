package Controllers;

import DAO.CarrinhoDAO;
import DAO.CarrinhoItemDAO;
import Entidades.Carrinho;
import Entidades.CarrinhoItem;
import Entidades.Sessao;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;

public class RemoverCarrinhoController {

    @FXML private TextField txtIdPlano;

    @FXML
    private void onRemoverClick() {
        try {
            int idPlano    = Integer.parseInt(txtIdPlano.getText());
            Carrinho carrinho = new CarrinhoDAO().Buscar(Sessao.getIdUsuario());

            new CarrinhoItemDAO().Remover(new CarrinhoItem(carrinho.getId(), idPlano, 0));

            new Alert(AlertType.INFORMATION, "Item removido!").showAndWait();
        } catch (Exception ex) {
            new Alert(AlertType.ERROR, "Erro ao remover.").showAndWait();
            ex.printStackTrace();
        }
    }

    @FXML
    private void onVoltarMenuClick(ActionEvent e) throws Exception {
        Parent menu = FXMLLoader.load(getClass().getResource("/Views/MenuNormal.fxml"));
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.setScene(new Scene(menu));
    }
}
