package com.example.restaurante.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.example.restaurante.models.Usuario;
import com.example.restaurante.models.UsuarioValidator;
import com.example.restaurante.repos.UsuarioRepository;
import com.example.restaurante.services.UsuarioService;

public class UsuariosController implements Initializable {
    UsuarioRepository repo = new UsuarioRepository();
    UsuarioValidator validator = new UsuarioValidator();

    Usuario usuario = new Usuario();

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

        usuario.setLogin(txtLogin.getText());
        usuario.setNome(txtNome.getText());
        usuario.setSenha(txtSenha.getText());

        service.salvarUsuario(usuario);

        atualizarTabela();
    }

    Callback<TableColumn<Usuario, Void>, TableCell<Usuario, Void>> cellFactory = param -> new TableCell<Usuario, Void>() {
        @Override
        public void updateItem(Void item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setGraphic(null);
            } else {
                Button btnEdit = new Button("Editar");
                btnEdit.setOnAction((ActionEvent event) -> {
                    Usuario data = getTableView().getItems().get(getIndex());
                    editar(data);
                });

                Button btnDel = new Button("Remover");
                btnDel.setOnAction((ActionEvent event) -> {
                    Usuario data = getTableView().getItems().get(getIndex());
                    remover(data.getId());
                });

                HBox hbox = new HBox(btnEdit, btnDel);
                hbox.setSpacing(10);

                setGraphic(hbox);
            }
        }
    };

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colLogin.setCellValueFactory(new PropertyValueFactory<>("login"));

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

    private void editar(Usuario data) {
        this.usuario = data;
        txtLogin.setText(data.getLogin());
        txtNome.setText(data.getNome());
        txtSenha.setText(data.getSenha());
    }
}
