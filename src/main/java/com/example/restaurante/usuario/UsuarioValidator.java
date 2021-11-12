package com.example.restaurante.usuario;

import java.util.ArrayList;
import java.util.List;

// Responsável por dizer se uma entidade está válida ou não
public class UsuarioValidator {
    public boolean isValid(Usuario usuario, List<String> erros) {
        if (usuario.getNome() == null) { // o nome está vazio
            erros.add("você deve fornecer um nome para o usuário");
        }

        if (usuario.getNome() != null && usuario.getNome().length() < 3) { // o nome está preenchido porém tem poucas letras
            erros.add("O nome do usuário deve possuir pelo menos 3 letras");
        }

        if (usuario.getLogin() == null) { // o login está vazio
            erros.add("Você deve fornecer um login para o usuário");
        }

        // TODO: Verificar se tem mais de um usuario com o mesmo login e dar erro - login deve ser unico

        if (usuario.getSenha() != null && usuario.getSenha().length() < 6) { // a senha tem menos de 6 caracteres
            erros.add("a senha deve ter ao menos 6 caracteres");
        }

        return erros.isEmpty(); // se a lista de erros está vazia, então é valido
    }
}
