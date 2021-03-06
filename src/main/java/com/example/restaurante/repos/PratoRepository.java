package com.example.restaurante.repos;

import com.example.restaurante.MySQLConnection;
import com.example.restaurante.models.Prato;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PratoRepository {
    private Connection db;

    public PratoRepository() {
        MySQLConnection mysql = new MySQLConnection();
        db = mysql.getConnection();

        try (PreparedStatement stm = db.prepareStatement(
                "CREATE TABLE IF NOT EXISTS `pratos` (`id` int(11) NOT NULL AUTO_INCREMENT, `nome` varchar(255) DEFAULT NULL, `ingredientes` text, `preco` double DEFAULT NULL, `imagem` varchar(255) DEFAULT NULL, PRIMARY KEY (`id`))")) {
            stm.execute();
        } catch (SQLException e) {
            System.err.println("Erro ao criar tabela pratos");
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    public void insert(Prato prato) {
        try (PreparedStatement stm = db
                .prepareStatement("INSERT INTO pratos (nome, ingredientes, preco, imagem) VALUES (?, ?, ?,? )")) {
            stm.setString(1, prato.getNome());
            stm.setString(2, prato.getIngredientes());
            stm.setDouble(3, prato.getPreco());
            stm.setString(4, prato.getImagem());

            stm.execute();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void update(Prato prato) {
        try (PreparedStatement stm = db
                .prepareStatement("UPDATE pratos SET nome = ?, ingredientes = ?, preco = ?, imagem = ? WHERE id = ?",
                        Statement.RETURN_GENERATED_KEYS)) {
            stm.setString(1, prato.getNome());
            stm.setString(2, prato.getIngredientes());
            stm.setDouble(3, prato.getPreco());
            stm.setString(4, prato.getImagem());
            stm.setInt(5, prato.getId());

            stm.execute();
            ResultSet rs = stm.getGeneratedKeys();
            if (rs.next()) {
                prato.setId(rs.getInt(1));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Prato> findAll() {
        try (Statement stm = db.createStatement()) {

            ResultSet result = stm.executeQuery("SELECT id, nome, ingredientes, preco, imagem FROM pratos");

            ArrayList<Prato> lista = new ArrayList<>();

            while (result.next()) {
                Prato prato = new Prato(
                        result.getString(2),
                        result.getString(3),
                        result.getDouble(4),
                        result.getString(5));
                prato.setId(result.getInt(1));
                lista.add(prato);
            }

            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return new ArrayList<>();
    }

    public void remover(int id) {
        try (PreparedStatement stm = db.prepareStatement("DELETE FROM pratos WHERE id = ?")) {
            stm.setInt(1, id);
            stm.execute();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
