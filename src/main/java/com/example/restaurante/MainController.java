package com.example.restaurante;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.io.IOException;

public class MainController {
    @FXML
    TabPane tabPane;

    @FXML
    public void sair() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RestauranteApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        RestauranteApplication.mainStage.setMaximized(false);
        RestauranteApplication.mainStage.setScene(scene);
        RestauranteApplication.mainStage.setMaximized(true);
    }

    @FXML
    public void listarUsuarios() throws IOException {
        Tab tab = new Tab("Cadastro de Usuários");
        FXMLLoader fxmlLoader = new FXMLLoader(RestauranteApplication.class.getResource("usuarios-view.fxml"));
        tab.setContent(fxmlLoader.load());
        tabPane.getTabs().add(tab);
    }

    @FXML
    public void listarPratos() throws IOException {
        Tab tab = new Tab("Cadastro de Pratos");
        FXMLLoader fxmlLoader = new FXMLLoader(RestauranteApplication.class.getResource("prato-view.fxml"));
        tab.setContent(fxmlLoader.load());
        tabPane.getTabs().add(tab);
    }
}
