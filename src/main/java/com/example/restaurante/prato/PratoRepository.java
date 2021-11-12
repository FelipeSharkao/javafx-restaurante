package com.example.restaurante.prato;

import com.example.restaurante.MySQLConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PratoRepository {
    private Connection db;
    public PratoRepository() {
        MySQLConnection mysql = new MySQLConnection();
        db = mysql.getConnection();
    }

    public void insert(Prato prato) {
        try(PreparedStatement stm = db.prepareStatement("INSERT INTO pratos (nome, ingredientes, preco, imagem) VALUES (?, ?, ?,? )")) {
            stm.setString(1, prato.getNome());
            stm.setString(2, prato.getIngredientes());
            stm.setDouble(3, prato.getPreco());
            stm.setString(4, prato.getImagem());

            stm.execute();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Prato> findAll() {
        try(Statement stm = db.createStatement()) { // Prepara o comando

            ResultSet result = stm.executeQuery("SELECT id, nome, ingredientes, preco, imagem FROM pratos"); // executa a consulta

            ArrayList<Prato> lista = new ArrayList<>(); // cria uma lista para receber o resultado

            while (result.next()) { // enquanto houver linhas para ler
                Prato prato = new Prato();
                prato.setId(result.getInt(1));
                prato.setNome(result.getString(2));
                prato.setIngredientes(result.getString(3));
                prato.setPreco(result.getDouble(4));
                prato.setImagem(result.getString(5));
                lista.add(prato);
            } // faz de novo até acabar

            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return  null;
    }

    public void remover(int id) {
        try(PreparedStatement stm = db.prepareStatement("DELETE FROM pratos WHERE id = ?")) {
            stm.setInt(1, id);
            stm.execute();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}


