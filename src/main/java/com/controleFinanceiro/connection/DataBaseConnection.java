package com.controleFinanceiro.connection;
import io.github.cdimascio.dotenv.Dotenv;

import java.sql.*;
import java.util.*;

public class DataBaseConnection {
    private static DataBaseConnection instance;
    private Connection connection;
    Dotenv dotenv = Dotenv.load();

    public DataBaseConnection() {
        String url = dotenv.get("DB_URL");
        String usuario = dotenv.get("DB_USER");
        String senha = dotenv.get("DB_PASSWORD");
        try {
            connection = DriverManager
                    .getConnection(url, usuario, senha);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static DataBaseConnection getInstance() {
        if(Objects.isNull(instance)){
            instance = new DataBaseConnection();
        }
        return instance;
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Não foi possivel fechar conexão");
            }
        }
    }

    public static void closeStatemant(PreparedStatement pst) {
        if (pst != null) {
            try {
                pst.close();
            } catch (SQLException e) {
                System.out.println("Não foi possivel fechar conexão");
            }
        }
    }

    public static void closeRs(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                System.out.println("Não foi possivel fechar conexão");
            }
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
