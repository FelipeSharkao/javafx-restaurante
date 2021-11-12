package com.example.restaurante.prato;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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
        Prato prato = new Prato();
        prato.setNome(this.txtNome.getText());
        prato.setIngredientes(this.txtIngredientes.getText());
        prato.setPreco(Double.parseDouble(this.txtPreco.getText()));
        prato.setImagem(this.txtImagem.getText());

        this.service.salvar(prato);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Salvo com sucesso");
        alert.show();
    }

}
