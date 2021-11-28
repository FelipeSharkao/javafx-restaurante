package com.example.restaurante.controllers;

import com.example.restaurante.RestauranteApplication;
import com.example.restaurante.models.Prato;
import com.example.restaurante.models.PratoValidator;
import com.example.restaurante.repos.PratoRepository;
import com.example.restaurante.services.PratoService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PratoController implements Initializable {
    PratoRepository repo = new PratoRepository();
    PratoValidator validator = new PratoValidator();

    PratoService service = new PratoService(validator, repo);

    @FXML
    TableView<Prato> tbPratos;

    @FXML
    TableColumn<Prato, String> colNome;

    @FXML
    TableColumn<Prato, String> colIngredientes;

    @FXML
    TableColumn<Prato, Double> colPreco;

    @FXML
    TableColumn<Prato, String> colImagem;

    @FXML
    TableColumn<Prato, Void> colAction;

    Callback<TableColumn<Prato, Void>, TableCell<Prato, Void>> actionFactory = param -> new TableCell<>() {

        private final Button btn = new Button("Remover");
        {
            btn.setOnAction((ActionEvent event) -> {
                Prato data = getTableView().getItems().get(getIndex());
                remover(data.getId());
            });
        }

        @Override
        public void updateItem(Void item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setGraphic(null);
            } else {
                setGraphic(btn);
            }
        }
    };

    Callback<TableColumn<Prato, String>, TableCell<Prato, String>> imgFactory = param -> new TableCell<>() {

        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setGraphic(null);
            } else {
                try {
                    ImageView img = new ImageView(item);
                    setGraphic(img);
                } catch (IllegalArgumentException e) {
                    setGraphic(null);
                }
            }
        }

    };

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        this.colIngredientes.setCellValueFactory(new PropertyValueFactory<>("ingredientes"));
        this.colPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        this.colImagem.setCellValueFactory(new PropertyValueFactory<>("imagem"));
        this.colImagem.setCellFactory(this.imgFactory);
        this.colAction.setCellFactory(this.actionFactory);

        this.atualizarTabela();
    }

    private void atualizarTabela() {
        List<Prato> pratos = this.service.getAll();
        System.out.println(pratos);

        tbPratos.getItems().setAll(pratos);
    }

    private void remover(int id) {
        this.service.remover(id);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Usuário removido com sucesso");
        alert.show();

        this.atualizarTabela();
    }

    @FXML
    private void adicionar() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RestauranteApplication.class.getResource("adicionar-prato-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        Stage stage = new Stage();
        stage.setTitle("Novo Prato");
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(RestauranteApplication.mainStage);
        stage.showAndWait();
        this.atualizarTabela();
    }

}
