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

public class InserirPlanoController {

    @FXML
    private TextField txtDescricao;

    @FXML
    private TextField txtPreco;

    @FXML
    private TextField txtDuracao;

    @FXML
    private void onSalvarClick() {
        try {
            System.out.println("Descrição: " + txtDescricao.getText());
            System.out.println("Preço: " + txtPreco.getText());
            System.out.println("Duração: " + txtDuracao.getText());
            String descricao = txtDescricao.getText();
            double preco = Double.parseDouble(txtPreco.getText());
            int duracao = Integer.parseInt(txtDuracao.getText());

            Plano plano = new Plano(descricao, preco, duracao);
            PlanoDAO planoDAO = new PlanoDAO();
            planoDAO.Inserir(plano);

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Plano Inserido");
            alert.setHeaderText(null);
            alert.setContentText("Plano inserido com sucesso!");
            alert.showAndWait();

        } catch (NumberFormatException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText("Erro ao inserir plano: " + e.getMessage());
            alert.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onVoltarMenuClick(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("/Views/MenuAdm.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(menu));
    }
}
