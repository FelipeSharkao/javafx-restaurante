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

public class EditarPratoController {
    PratoRepository repo = new PratoRepository();
    PratoValidator validator = new PratoValidator();

    PratoService service = new PratoService(validator, repo);

    Prato prato;

    @FXML
    TextField txtNome;

    @FXML
    TextArea txtIngredientes;

    @FXML
    TextField txtPreco;

    @FXML
    TextField txtImagem;
    

    public void setPrato(Prato prato) {
        this.prato = prato;
        txtNome.setText(prato.getNome());
        txtIngredientes.setText(prato.getIngredientes());
        txtPreco.setText(Double.toString(prato.getPreco()));
        txtImagem.setText(prato.getImagem());
    }

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

        if (prato == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Prato não encontrado");
            alert.setContentText("O prato não foi encontrado");
            alert.showAndWait();
            return;
        }
        prato.setNome(this.txtNome.getText());
        prato.setIngredientes(this.txtIngredientes.getText());
        prato.setPreco(preco);
        prato.setImagem(this.txtImagem.getText());

        this.service.salvar(prato);

        ((Stage) this.txtNome.getScene().getWindow()).close();
    }

}
