package com.example.restaurante.usuario;

import com.example.restaurante.RestauranteApplication;

import java.io.IOException;

import java.net.URL;

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

import java.util.List;
import java.util.ResourceBundle;

public class UsuariosController {
    UsuarioRepository repo = new UsuarioRepository();
    UsuarioValidator validator = new UsuarioValidator();

    @FXML
    TableView<Usuario> tbUsuarios;

    @FXML
    TableColumn<Usuario, String> colNome;

    @FXML
    TableColumn<Usuario, String> colLogin;

    @FXML
    TableColumn<Usuario, Void> actions;

    @FXML
    TextField txtNome;

    @FXML
    TextField txtLogin;

    @FXML
    PasswordField txtSenha;

    @FXML
    public void salvar() {
        UsuarioService service = new UsuarioService(validator, repo);

        Usuario usuario = new Usuario();
        usuario.setLogin(txtLogin.getText());
        usuario.setNome(txtNome.getText());
        usuario.setSenha(txtSenha.getText());

        service.salvarUsuario(usuario);
    }

    Callback<TableColumn<Usuario, Void>, TableCell<Usuario, Void>> cellFactory = param -> new TableCell<Usuario, Void>() {

        private final Button btn = new Button("Remover");
        {
            btn.setOnAction((ActionEvent event) -> {
                Usuario data = getTableView().getItems().get(getIndex());
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colNome.setCellValueFactory(new PropertyValueFactory<Usuario, String>("nome"));
        colLogin.setCellValueFactory(new PropertyValueFactory<Usuario, String>("login"));

        actions.setCellFactory(cellFactory);

        this.atualizarTabela();
    }

    private void atualizarTabela() {
        List<Usuario> todosOsUsuarios = this.repo.findAll();
        tbUsuarios.getItems().setAll(todosOsUsuarios);
    }

    private void remover(int id) {
        this.repo.remover(id);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Usuário removido com sucesso");
        alert.show();

        atualizarTabela();
    }
}