package com.example.restaurante.controllers;

import com.example.restaurante.RestauranteApplication;
import com.example.restaurante.models.Usuario;
import com.example.restaurante.models.UsuarioValidator;
import com.example.restaurante.repos.UsuarioRepository;
import com.example.restaurante.services.UsuarioService;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController {
    UsuarioRepository repo = new UsuarioRepository();
    UsuarioValidator validator = new UsuarioValidator();

    @FXML
    TextField txtLogin;

    @FXML
    PasswordField txtSenha;

    @FXML
    public void entrar() throws IOException {
        String login = txtLogin.getText();
        String senha = txtSenha.getText();

        UsuarioService service = new UsuarioService(validator, repo);
        Usuario usuario = service.getByLogin(login);

        if (usuario != null && usuario.getSenha().equals(senha)) {
            FXMLLoader fxmlLoader = new FXMLLoader(RestauranteApplication.class.getResource("main-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            RestauranteApplication.mainStage.setMaximized(false);
            RestauranteApplication.mainStage.setScene(scene);
            RestauranteApplication.mainStage.setMaximized(true);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Login ou senha inválidos.");
            alert.show();
        }
    }
}