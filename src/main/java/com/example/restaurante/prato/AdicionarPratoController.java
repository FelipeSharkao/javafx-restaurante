package com.example.restaurante.prato;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AdicionarPratoController {
    PratoRepository repo = new PratoRepository();
    PratoValidator validator = new PratoValidator();

    PratoService service = new PratoService(validator, repo);

    @FXML
    TextField txtNome;

    @FXML
    TextArea txtIngredientes;

    @FXML
    TextField txtPreco;

    @FXML
    TextField txtImagem;

    @FXML
    private void salvar() {
        double preco;
        try {
            preco = Double.parseDouble(txtPreco.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Preço inválido");
            alert.setContentText("O preço deve ser um número");
            alert.showAndWait();
            return;
        }

        Prato prato = new Prato(
                this.txtNome.getText(),
                this.txtIngredientes.getText(),
                preco,
                this.txtImagem.getText());

        this.service.salvar(prato);

        ((Stage) this.txtNome.getScene().getWindow()).close();
    }

}
