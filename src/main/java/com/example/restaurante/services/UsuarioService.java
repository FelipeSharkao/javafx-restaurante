package com.example.restaurante.services;

import javafx.scene.control.Alert;

import java.util.ArrayList;

import com.example.restaurante.models.Usuario;
import com.example.restaurante.models.UsuarioValidator;
import com.example.restaurante.repos.UsuarioRepository;

// Responsável pelas regras de funcionamento e negócio
public class UsuarioService {
    private UsuarioValidator validator;
    private UsuarioRepository repository;

    // Inversão de dependencia -> Se eu preciso de algo para funcionar,
    // este algo me será fornecido
    public UsuarioService(UsuarioValidator validador, UsuarioRepository repository) {
        this.validator = validador;
        this.repository = repository;
    }

    public void salvarUsuario(Usuario usuario) {
        ArrayList<String> erros = new ArrayList<>();

        if (!validator.isValid(usuario, erros)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Não foi possivel salvar seu usuário");
            alert.setContentText(String.join("\n", erros));
            alert.show();
            return;
        }

        if (usuario.getId() == null){
            this.repository.insert(usuario);
        } else {
            this.repository.update(usuario);
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Usuário salvo com sucesso");
        alert.show();
    }

    public Usuario getByLogin(String login) {
        if (login == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Você deve fornecer um login");
            alert.show();
            return null;
        }

        return this.repository.findByLogin(login);
    }
}
