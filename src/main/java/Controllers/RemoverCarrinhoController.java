package Controllers;

import DAO.CarrinhoDAO;
import DAO.CarrinhoItemDAO;
import DAO.PlanoDAO;
import Entidades.Carrinho;
import Entidades.CarrinhoItem;
import Entidades.Plano;
import Entidades.Sessao;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;

import java.util.List;

public class RemoverCarrinhoController {

    @FXML
    private TextArea itensTextArea;
    @FXML
    private TextField txtIdItem;

    @FXML
    private void initialize() {
        try {
            Carrinho carrinho = new CarrinhoDAO().Buscar(Sessao.getIdUsuario());
            List<CarrinhoItem> itens = new CarrinhoItemDAO().BuscarTodosItens(carrinho.getId());

            if (!itens.isEmpty()) {
                StringBuilder itensText = new StringBuilder();
                double total = 0;
                for (CarrinhoItem item : itens) {
                    Plano plano = new PlanoDAO().BuscarPlano(item.getIdPlano());
                    double preco = plano.getPreco();
                    total += preco * item.getQuantidade();
                    itensText.append("Id: ").append(item.getId())
                            .append(" - Descrição: ").append(plano.getDescricao())
                            .append(" - Quantidade: ").append(item.getQuantidade())
                            .append(" - Preço: ").append(preco * item.getQuantidade()).append("\n");
                }

                itensTextArea.setText(itensText.toString());
            } else {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Carrinho Vazio");
                alert.setHeaderText(null);
                alert.setContentText("Seu carrinho está vazio.");
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText("Erro ao carregar os itens do carrinho.");
            alert.showAndWait();
        }
    }

    @FXML
    private void onRemoverClick() {
        try {
            int idItem = Integer.parseInt(txtIdItem.getText());
            new CarrinhoItemDAO().Remover(idItem);

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
