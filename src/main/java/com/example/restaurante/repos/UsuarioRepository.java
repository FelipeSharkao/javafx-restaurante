package com.example.restaurante.repos;

import com.example.restaurante.MySQLConnection;
import com.example.restaurante.models.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// responsável por guardar e recuperar um dado
public class UsuarioRepository {
    private Connection db;

    public UsuarioRepository() {
        MySQLConnection mysql = new MySQLConnection();
        db = mysql.getConnection();

        try (PreparedStatement stm = db.prepareStatement(
                "CREATE TABLE IF NOT EXISTS `usuario` (`id` int(11) NOT NULL AUTO_INCREMENT, `nome` varchar(45) NOT NULL, `login` varchar(45) NOT NULL, `senha` varchar(25) NOT NULL, PRIMARY KEY (`id`))")) {
            stm.execute();
        } catch (SQLException e) {
            System.err.println("Erro ao criar tabela usuario");
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    public void insert(Usuario usuario) {
        try (PreparedStatement stm = db.prepareStatement("INSERT INTO usuario (nome, login, senha) VALUES (?, ?, ?)")) {
            stm.setString(1, usuario.getNome());
            stm.setString(2, usuario.getLogin());
            stm.setString(3, usuario.getSenha());

            stm.execute();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void update(Usuario usuario) {
        try (PreparedStatement stm = db
                .prepareStatement("UPDATE usuario SET nome = ?, login = ?, senha = ?  WHERE id = ?")) {
            stm.setString(1, usuario.getNome());
            stm.setString(2, usuario.getLogin());
            stm.setString(3, usuario.getSenha());
            stm.setInt(4, usuario.getId());

            stm.execute();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Usuario findByLogin(String login) {
        try (PreparedStatement stm = db
                .prepareStatement("SELECT id, nome, login, senha FROM usuario WHERE login = ?")) {
            stm.setString(1, login);

            ResultSet result = stm.executeQuery();

            Usuario usuario = null;
            if (result.next()) {
                usuario = new Usuario();
                usuario.setId(result.getInt(1));
                usuario.setNome(result.getString(2));
                usuario.setLogin(result.getString(3));
                usuario.setSenha(result.getString(4));
            }
            return usuario;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public List<Usuario> findAll() {
        ArrayList<Usuario> lista = new ArrayList<>();

        try (Statement stm = db.createStatement()) {
            ResultSet result = stm.executeQuery("SELECT id, nome, login, senha FROM usuario");

            while (result.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(result.getInt(1));
                usuario.setNome(result.getString(2));
                usuario.setLogin(result.getString(3));
                usuario.setSenha(result.getString(4));
                lista.add(usuario);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return lista;
    }

    public void remover(int id) {
        try (PreparedStatement stm = db.prepareStatement("DELETE FROM usuario WHERE id = ?")) {
            stm.setInt(1, id);
            stm.execute();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
