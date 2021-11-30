package com.example.restaurante.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import com.example.restaurante.models.Mesa;
import com.example.restaurante.repos.MesaRepository;
import com.example.restaurante.services.MesaService;

public class AdmMesasController implements Initializable {
    MesaRepository repo = new MesaRepository();
    MesaService service = new MesaService(repo);

    Mesa mesa = new Mesa(0);

    @FXML
    TableView<Mesa> tbMesas;

    @FXML
    TableColumn<Mesa, Integer> colNumero;

    @FXML
    TableColumn<Mesa, Boolean> colOcupado;

    @FXML
    TableColumn<Mesa, Boolean> colAtivo;

    @FXML
    TableColumn<Mesa, Void> colAction;

    @FXML
    TextField txtNumero;

    // TODO: Deveria extrair isso pra uma classe separada
    private TableCell<Mesa, Void> actionFactory() {
        return new TableCell<>() {
            @Override
            public void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    Button btnEdit = new Button("Editar");
                    btnEdit.setOnAction((ActionEvent event) -> {
                        Mesa data = getTableView().getItems().get(getIndex());
                        editar(data);
                    });

                    Button btnDel = new Button("Remover");
                    btnDel.setOnAction((ActionEvent event) -> {
                        Mesa data = getTableView().getItems().get(getIndex());
                        remover(data);
                    });

                    HBox hbox = new HBox(btnEdit, btnDel);
                    hbox.setSpacing(10);

                    setGraphic(hbox);
                }
            }
        };
    }

    private TableCell<Mesa, Boolean> boolFactory(Consumer<Mesa> set, Consumer<Mesa> unset) {
        return new TableCell<>() {
            @Override
            public void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    CheckBox checkBox = new CheckBox();
                    checkBox.setSelected(item);
                    checkBox.setOnAction(ev -> {
                        Mesa data = getTableView().getItems().get(getIndex());
                        if (checkBox.isSelected()) {
                            set.accept(data);
                        } else {
                            unset.accept(data);
                        }
                        service.salvarMesa(data);
                    });
                    setGraphic(checkBox);
                }
            }
        };
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.colNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        this.colOcupado.setCellValueFactory(new PropertyValueFactory<>("ocupado"));
        this.colOcupado.setCellFactory(tc -> boolFactory(m -> m.setOcupado(true), m -> m.setOcupado(false)));
        this.colAtivo.setCellValueFactory(new PropertyValueFactory<>("ativo"));
        this.colAtivo.setCellFactory(tc -> boolFactory(m -> m.setAtivo(true), m -> m.setAtivo(false)));
        this.colAction.setCellFactory(tc -> this.actionFactory());

        this.atualizarTabela();
    }

    @FXML
    public void salvar() {
        int numero;
        try {
            numero = Integer.parseInt(txtNumero.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Número inválido");
            alert.setContentText("O número deve conter apenas dígitos");
            alert.showAndWait();
            return;
        }

        mesa.setNumero(numero);
        service.salvarMesa(mesa);

        atualizarTabela();
    }

    private void remover(Mesa data) {
        this.service.remover(data);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Usuário removido com sucesso");
        alert.show();

        atualizarTabela();
    }

    private void editar(Mesa data) {
        this.mesa = data;
        txtNumero.setText(Integer.toString(data.getNumero()));
    }

    private void atualizarTabela() {
        List<Mesa> mesas = this.service.getAll();
        System.out.println(mesas);

        tbMesas.getItems().setAll(mesas);
    }
}
