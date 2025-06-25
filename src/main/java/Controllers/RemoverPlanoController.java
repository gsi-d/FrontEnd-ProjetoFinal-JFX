package Controllers;

import DAO.PlanoDAO;
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

public class RemoverPlanoController {

    @FXML
    private TextField idPlanoField;

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
