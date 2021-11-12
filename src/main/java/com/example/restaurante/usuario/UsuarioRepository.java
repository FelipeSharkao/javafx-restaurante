package com.example.restaurante.usuario;

import com.example.restaurante.MySQLConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// responsável por guardar e recuperar um dado
public class UsuarioRepository {
    /**
     *
     */
    private static final String EX_HEADER = "[EXCEPTION]";
    private Connection db;

    public UsuarioRepository() {
        MySQLConnection mysql = new MySQLConnection();
        db = mysql.getConnection();
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
                .prepareStatement("UPDATE usuario SET name = ?, login = ?, senha = ?  WHERE id = ?")) {
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
            ResultSet result = stm.executeQuery("SELECT (id, nome, login, senha) FROM usuarios");

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
        try (PreparedStatement stm = db.prepareStatement("DELETE FROM usuarios WHERE id = ?")) {
            stm.setInt(1, id);
            stm.execute();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
