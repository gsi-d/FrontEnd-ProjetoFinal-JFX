package Controllers;

import DAO.CarrinhoDAO;
import DAO.CarrinhoItemDAO;
import DAO.PlanoDAO;
import Entidades.Carrinho;
import Entidades.CarrinhoItem;
import Entidades.Plano;
import Entidades.Sessao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class RemoverPlanoController {

    @FXML
    private TextArea planosTextArea;
    @FXML
    private TextField idPlanoField;

    @FXML
    private void initialize() {
        try {
            List<Plano> planos = new PlanoDAO().BuscarTodosPlanos();

            if (!planos.isEmpty()) {
                StringBuilder itensText = new StringBuilder();
                double total = 0;
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
    private void onRemoverClick() throws SQLException {
        try {
            int idPlano = Integer.parseInt(idPlanoField.getText());

            PlanoDAO planoDAO = new PlanoDAO();
            planoDAO.Remover(idPlano);

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Plano Removido");
            alert.setHeaderText(null);
            alert.setContentText("Plano removido com sucesso!");
            alert.showAndWait();
        } catch (NumberFormatException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText("Erro ao remover plano: Verifique o ID.");
            alert.showAndWait();
        }
    }


    @FXML
    public void onVoltarMenuClick(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("/Views/MenuAdm.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(menu));
    }
}
