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

public class AdicionarCarrinhoController {

    @FXML private TextField txtIdPlano;
    @FXML private TextField txtQuantidade;

    @FXML
    private void onAdicionarClick() {
        try {
            int idPlano = Integer.parseInt(txtIdPlano.getText());
            Carrinho carrinho = new CarrinhoDAO().Buscar(Sessao.getIdUsuario());
            if(carrinho == null) {
                new CarrinhoDAO().Inserir(Sessao.getIdUsuario());
                carrinho = new CarrinhoDAO().Buscar(Sessao.getIdUsuario());
            }
            int quantidade = Integer.parseInt(txtQuantidade.getText());

            CarrinhoItem item = new CarrinhoItem(idPlano, carrinho.getId(), quantidade);
            new CarrinhoItemDAO().Adicionar(item);

            Alert a = new Alert(AlertType.INFORMATION);
            a.setTitle("Sucesso");
            a.setHeaderText(null);
            a.setContentText("Item adicionado ao carrinho com sucesso!");
            a.showAndWait();

        } catch (NumberFormatException ex) {
            new Alert(AlertType.ERROR, "Verifique os campos numéricos.").showAndWait();
        } catch (Exception ex) {
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
