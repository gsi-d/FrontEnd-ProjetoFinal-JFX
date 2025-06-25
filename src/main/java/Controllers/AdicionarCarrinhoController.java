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

public class AdicionarCarrinhoController {

    @FXML private TextField txtIdPlano;
    @FXML private TextField txtQuantidade;
    @FXML private TextArea planosTextArea;

    @FXML
    private void initialize() {
        try {
            List<Plano> planos = new PlanoDAO().BuscarTodosPlanos();

            if (!planos.isEmpty()) {
                StringBuilder itensText = new StringBuilder();
                for (Plano plano : planos) {
                    itensText.append("Id: ").append(plano.getId())
                            .append(" - Descrição: ").append(plano.getDescricao())
                            .append(" - Preço: ").append(plano.getPreco())
                            .append(" - Duração: ").append(plano.getDuracao()).append(" meses.").append("\n");
                }

                planosTextArea.setText(itensText.toString());
            } else {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Alerta");
                alert.setHeaderText(null);
                alert.setContentText("Não existem planos cadastrados");
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText("Erro ao carregar os planos.");
            alert.showAndWait();
        }
    }
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

            CarrinhoItem item = new CarrinhoItem(carrinho.getId(),idPlano,  quantidade);
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
