package com.example.restaurante.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.io.IOException;

import com.example.restaurante.RestauranteApplication;

public class MainController {
    @FXML
    TabPane tabPane;

    private void addTab(String fxml, String title) throws IOException {
        Tab tab = new Tab(title);
        FXMLLoader fxmlLoader = new FXMLLoader(RestauranteApplication.class.getResource(fxml));
        tab.setContent(fxmlLoader.load());
        tabPane.getTabs().add(tab);
    }

    @FXML
    public void sair() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RestauranteApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        RestauranteApplication.mainStage.setMaximized(false);
        RestauranteApplication.mainStage.setScene(scene);
        RestauranteApplication.mainStage.setMaximized(true);
    }

    @FXML
    public void cardapio() throws IOException {
        addTab("cardapio-view.fxml", "Cardápio");
    }

    @FXML
    public void mesas() throws IOException {
        addTab("mesas-view.fxml", "Mesas");
    }

    @FXML
    public void listarUsuarios() throws IOException {
        addTab("usuarios-view.fxml", "Cadastro de Usuários");
    }

    @FXML
    public void listarPratos() throws IOException {
        addTab("prato-view.fxml", "Cadastro de Pratos");
    }
    
    @FXML
    public void listarMesas() throws IOException {
        addTab("adm-mesas-view.fxml", "Cadastro de Pratos");
    }
}
