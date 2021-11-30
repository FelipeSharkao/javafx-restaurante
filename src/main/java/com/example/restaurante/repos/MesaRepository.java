package com.example.restaurante.repos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.example.restaurante.MySQLConnection;
import com.example.restaurante.models.Mesa;

public class MesaRepository {
    private Connection db;

    public MesaRepository() {
        MySQLConnection mysql = new MySQLConnection();
        db = mysql.getConnection();

        try (PreparedStatement stm = db.prepareStatement(
                "CREATE TABLE IF NOT EXISTS `mesas` (`id` int(11) NOT NULL AUTO_INCREMENT, `numero` smallint(6) UNSIGNED NOT NULL, `ocupado` bool DEFAULT FALSE, `ativo` bool DEFAULT TRUE, PRIMARY KEY (`id`))")) {
            stm.execute();
        } catch (SQLException e) {
            System.err.println("Erro ao criar tabela mesas");
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    public void insert(Mesa mesa) {
        try (PreparedStatement stm = db.prepareStatement(
                "INSERT INTO mesas (numero, ocupado, ativo) VALUES (?, ?, ?)")) {
            stm.setInt(1, mesa.getNumero());
            stm.setBoolean(2, mesa.isOcupado());
            stm.setBoolean(3, mesa.isAtivo());

            stm.execute();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void update(Mesa mesa) {
        try (PreparedStatement stm = db.prepareStatement(
                "UPDATE mesas SET numero = ?, ocupado = ?, ativo = ? WHERE id = ?",
                Statement.RETURN_GENERATED_KEYS)) {
            stm.setInt(1, mesa.getNumero());
            stm.setBoolean(2, mesa.isOcupado());
            stm.setBoolean(3, mesa.isAtivo());
            stm.setInt(4, mesa.getId());

            stm.execute();
            ResultSet rs = stm.getGeneratedKeys();
            if (rs.next()) {
                mesa.setId(rs.getInt(1));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Mesa> findAll() {
        ArrayList<Mesa> lista = new ArrayList<>();

        try (Statement stm = db.createStatement()) {
            ResultSet result = stm.executeQuery("SELECT id, numero, ocupado, ativo FROM mesas");

            while (result.next()) {
                Mesa mesa = new Mesa(result.getInt(2), result.getBoolean(3), result.getBoolean(4));
                mesa.setId(result.getInt(1));
                lista.add(mesa);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return lista;
    }

    public List<Mesa> findAllAtivos() {
        ArrayList<Mesa> lista = new ArrayList<>();

        try (Statement stm = db.createStatement()) {
            ResultSet result = stm.executeQuery("SELECT id, numero, ocupado FROM mesas WHERE ativo = TRUE");

            while (result.next()) {
                Mesa mesa = new Mesa(result.getInt(2), result.getBoolean(3), true);
                mesa.setId(result.getInt(1));
                lista.add(mesa);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return lista;
    }

    public void delete(Mesa mesa) {
        try (PreparedStatement stm = db.prepareStatement(
                "DELETE FROM mesas WHERE id = ?")) {
            stm.setInt(1, mesa.getId());

            stm.execute();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
