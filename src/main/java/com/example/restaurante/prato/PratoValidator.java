package com.example.restaurante.prato;

import java.util.List;

public class PratoValidator {
    public boolean isValid(Prato prato, List<String> erros) {
        if (prato.getNome() == null) {
            erros.add("você deve fornecer um nome para o prato");
        }


        if (prato.getPreco() <= 0) {
            erros.add("Você deve fornecer preço para o prato");
        }

        return erros.isEmpty(); // se a lista de erros está vazia, então é valido
    }
}
