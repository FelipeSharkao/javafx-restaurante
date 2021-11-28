package com.example.restaurante.services;

import javafx.scene.control.Alert;

import java.util.ArrayList;
import java.util.List;

import com.example.restaurante.models.Prato;
import com.example.restaurante.models.PratoValidator;
import com.example.restaurante.repos.PratoRepository;

public class PratoService {
    private PratoValidator validator;
    private PratoRepository repository;

    public PratoService(PratoValidator validador, PratoRepository repository) {
        this.validator = validador;
        this.repository = repository;
    }

    public void salvar(Prato prato) {
        ArrayList<String> erros = new ArrayList<>();

        if (!validator.isValid(prato, erros)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Não foi possivel salvar seu prato");
            alert.setContentText(String.join("\n", erros));
            alert.show();
            return;
        }

        if (prato.getId() == null) {
            repository.insert(prato);
        } else {
            repository.update(prato);
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Prato salvo com sucesso");
        alert.show();
    }

    public List<Prato> getAll() {
        return this.repository.findAll();
    }

    public void remover(int id) {
        this.repository.remover(id);
    }
}
