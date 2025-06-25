package Controllers;

import DAO.PlanoDAO;
import Entidades.Plano;
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

public class AtualizarPlanoController {

    @FXML
    private TextField txtIdPlano;

    @FXML
    private TextField txtDescricao;

    @FXML
    private TextField txtPreco;

    @FXML
    private TextField txtDuracao;

    @FXML
    private void onAtualizarClick() throws SQLException {
        try {
            System.out.println("Id: " + txtIdPlano.getText());
            System.out.println("Descrição: " + txtDescricao.getText());
            System.out.println("Preço: " + txtPreco.getText());
            System.out.println("Duração: " + txtDuracao.getText());
            int idPlano = Integer.parseInt(txtIdPlano.getText());
            String descricao = txtDescricao.getText();
            double preco = Double.parseDouble(txtPreco.getText());
            int duracao = Integer.parseInt(txtDuracao.getText());

            Plano plano = new Plano(descricao, preco, duracao, idPlano);
            PlanoDAO planoDAO = new PlanoDAO();
            planoDAO.Atualizar(plano);

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Plano Atualizado");
            alert.setHeaderText(null);
            alert.setContentText("Plano atualizado com sucesso!");
            alert.showAndWait();
        } catch (NumberFormatException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText("Erro ao atualizar plano: Verifique os campos.");
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
