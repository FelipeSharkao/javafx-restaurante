package com.example.restaurante;

import com.example.restaurante.usuario.Usuario;
import com.example.restaurante.usuario.UsuarioValidator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class RestauranteApplication extends Application {
    public static Stage mainStage = null;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RestauranteApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Gestão de Restaurantes Organizações Tabajara");
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();

        RestauranteApplication.mainStage = stage;
    }

    public static void main(String[] args) {
        launch();
    }
}