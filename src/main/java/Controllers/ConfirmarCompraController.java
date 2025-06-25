package Controllers;

import DAO.CarrinhoItemDAO;
import DAO.CarrinhoDAO;
import DAO.PlanoDAO;
import DAO.PlanoUsuarioDAO;
import Entidades.*;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import java.util.Date;
import java.util.List;

public class ConfirmarCompraController {

    @FXML private TextArea itensCarrinhoTextArea;
    @FXML private TextField txtTotalCarrinho;

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
                    itensText.append("Id: ").append(plano.getId())
                            .append(" - Descrição: ").append(plano.getDescricao())
                            .append(" - Quantidade: ").append(item.getQuantidade())
                            .append(" - Preço: ").append(preco * item.getQuantidade()).append("\n");
                }

                itensCarrinhoTextArea.setText(itensText.toString());
                txtTotalCarrinho.setText(String.valueOf(total));
            } else {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Carrinho Vazio");
                alert.setHeaderText(null);
                alert.setContentText("Seu carrinho está vazio.");
                alert.showAndWait();
                voltarMenu();
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
    private void onConfirmarCompraClick() {
        try {
            Carrinho carrinho = new CarrinhoDAO().Buscar(Sessao.getIdUsuario());
            List<CarrinhoItem> itens = new CarrinhoItemDAO().BuscarTodosItens(carrinho.getId());

            if (!itens.isEmpty()) {
                System.out.println("Confirmando compra...");
                for (CarrinhoItem item : itens) {
                    Plano plano = new PlanoDAO().BuscarPlano(item.getIdPlano());
                    double mensalidade = plano.getPreco() * item.getQuantidade();
                    PlanoUsuario planoUsuario = new PlanoUsuario(item.getIdPlano(), Sessao.getIdUsuario(), new Date(), plano.getDuracao(), 1, mensalidade); // Exemplo de pagamento Pix (1)
                    new PlanoUsuarioDAO().Inserir(planoUsuario);
                }

                for (CarrinhoItem item : itens) {
                    new CarrinhoItemDAO().Remover(item);
                }
                new CarrinhoDAO().Remover(Sessao.getIdUsuario());

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Compra Confirmada");
                alert.setHeaderText(null);
                alert.setContentText("Sua compra foi confirmada com sucesso!");
                alert.showAndWait();

                voltarMenu();
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
            alert.setContentText("Erro ao confirmar a compra.");
            alert.showAndWait();
        }
    }

    @FXML
    private void onCancelarCompraClick() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Compra Cancelada");
        alert.setHeaderText(null);
        alert.setContentText("Sua compra foi cancelada.");
        alert.showAndWait();

        voltarMenu();
    }

    @FXML
    private void onVoltarMenuClick() {
        voltarMenu();
    }

    private void voltarMenu() {
        try {
            Parent menu = FXMLLoader.load(getClass().getResource("/Views/MenuNormal.fxml"));
            Stage stage = (Stage) itensCarrinhoTextArea.getScene().getWindow();
            stage.setScene(new Scene(menu));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
