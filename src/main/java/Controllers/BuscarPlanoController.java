package Controllers;

import DAO.PlanoDAO;
import Entidades.Plano;
import Entidades.Sessao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;

public class BuscarPlanoController {

    @FXML
    private TextField txtPlano;

    @FXML
    private void onBuscarClick() throws SQLException {
        try {
            int idPlano = Integer.parseInt(txtPlano.getText());

            PlanoDAO planoDAO = new PlanoDAO();
            Plano plano = planoDAO.BuscarPlano(idPlano);

            if (plano != null) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Plano Encontrado");
                alert.setHeaderText(null);
                alert.setContentText("Descrição: " + plano.getDescricao() + " -  Preço : " + plano.getPreco() + " - Duração: " + plano.getDuracao());
                alert.showAndWait();
            } else {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Plano Não Encontrado");
                alert.setHeaderText(null);
                alert.setContentText("Plano com ID " + idPlano + " não encontrado.");
                alert.showAndWait();
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText("Erro ao buscar plano: Verifique o ID.");
            alert.showAndWait();
        }
    }

    @FXML
    public void onVoltarMenuClick(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent menu = FXMLLoader.load(Sessao.getIsAdmin() == true ? getClass().getResource("/Views/MenuAdm.fxml") : getClass().getResource("/Views/MenuNormal.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(menu));
    }
}
