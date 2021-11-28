package com.example.restaurante.controllers;

import com.example.restaurante.models.Prato;
import com.example.restaurante.models.PratoValidator;
import com.example.restaurante.repos.PratoRepository;
import com.example.restaurante.services.PratoService;

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
