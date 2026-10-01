package com.controleFinanceiro.dao.impl;
import com.controleFinanceiro.connection.DataBaseConnection;
import com.controleFinanceiro.dao.Dao;
import com.controleFinanceiro.exceptions.ContasNotFoundException;
import com.controleFinanceiro.model.entities.Contas;

import java.math.*;
import java.sql.*;
import java.util.*;

public class ContasDaoImpl implements Dao<Contas> {
    private Connection connection;

    public ContasDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Contas findById(int id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String sqlConsultaId = "SELECT * FROM contas WHERE id = ?";
            preparedStatement = connection.prepareStatement(sqlConsultaId);
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return getContas(resultSet);
            }else {
                throw new ContasNotFoundException("Contas not found");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DataBaseConnection.closeRs(resultSet);
            DataBaseConnection.closeStatemant(preparedStatement);
        }
        return null;
    }

    @Override
    public List<Contas> getAll() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Contas> lista = new ArrayList<>();

        try {
            String sqlConsultaId = "SELECT * FROM contas";
            preparedStatement = connection.prepareStatement(sqlConsultaId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                lista.add(getContas(resultSet));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DataBaseConnection.closeRs(resultSet);
            DataBaseConnection.closeStatemant(preparedStatement);
        }
        return lista;
    }

    @Override
    public void insert(Contas contas) {
        PreparedStatement preparedStatement = null;

        try {
            String sqlConsultaId = "INSERT INTO contas(id_conta, nome, email, cpf) VALUES (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sqlConsultaId);
            preparedStatement.setInt(1, contas.getId());
            preparedStatement.setString(2, contas.getNome());
            preparedStatement.setString(3, contas.getEmail());
            preparedStatement.setString(4, contas.getCpf());
            preparedStatement.executeQuery();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DataBaseConnection.closeStatemant(preparedStatement);
        }
    }

    @Override
    public void update(Contas contas, String[] params) {

    }

    @Override
    public void delete(Contas contas) {

    }

    private static Contas getContas(ResultSet resultSet) throws SQLException {
        String nome = resultSet.getString("nome");
        String email = resultSet.getString("email");
        String cpf = resultSet.getString("cpf");
        int id = resultSet.getInt("id_conta");
        BigDecimal saldo = resultSet.getBigDecimal("saldo");
        return new Contas(id, nome, email, cpf, saldo);
    }
}
