package com.example.restaurante.services;

import java.util.List;

import com.example.restaurante.models.Mesa;
import com.example.restaurante.repos.MesaRepository;

public class MesaService {
    private MesaRepository mesaRepository;

    public MesaService(MesaRepository mesaRepository) {
        this.mesaRepository = mesaRepository;
    }

    public void salvarMesa(Mesa mesa) {
        if (mesa.getId() == null) {
            mesaRepository.insert(mesa);
        } else {
            mesaRepository.update(mesa);
        }
    }

    public List<Mesa> getAll() {
        return mesaRepository.findAll();
    }

    public List<Mesa> getAllAtivos() {
        return mesaRepository.findAllAtivos();
    }

    public void remover(Mesa mesa) {
        mesaRepository.delete(mesa);
    }
}
