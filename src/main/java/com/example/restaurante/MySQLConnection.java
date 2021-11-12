package com.example.restaurante;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Formatter;

public class MySQLConnection {
    private Dotenv dotenv;
    private Connection cnn;

    public Connection getConnection() {
        try {
            if (dotenv == null) {
                dotenv = Dotenv.configure().load();
            }
            if (cnn == null) {
                Formatter fmt = new Formatter();
                Class.forName("com.mysql.cj.jdbc.Driver");
                cnn = DriverManager.getConnection(fmt.format("jdbc:mysql://%s:%s/%s?user=%s&password=%s",
                        dotenv.get("MYSQL_HOST"), dotenv.get("MYSQL_PORT"), dotenv.get("MYSQL_NAME"),
                        dotenv.get("MYSQL_USER"), dotenv.get("MYSQL_PASSWORD")).toString());
                cnn.setAutoCommit(true);
            }
            return cnn;
        } catch (SQLException | ClassNotFoundException ex) {
            System.err.println("[EXCEPTION]");
            System.err.println(ex);
            return null;
        }
    }
}
