package com.example.restaurante.services;

import java.util.List;

import com.example.restaurante.models.Prato;
import com.example.restaurante.repos.PratoRepository;

public class CardapioService {
    private PratoRepository repository;

    public CardapioService(PratoRepository repository) {
        this.repository = repository;
    }

    public List<Prato> getAll() {
        return this.repository.findAll();
    }
}
