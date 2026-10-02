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
            String sqlConsultaId = "SELECT * FROM contas WHERE id_conta = ?";
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
            int resultado = preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DataBaseConnection.closeStatemant(preparedStatement);
        }
    }

    @Override
    public int update(Contas c) {
        PreparedStatement st = null;
        findById(c.getId());
        try {
            String update = "UPDATE contas SET nome = ?, cpf = ?, email = ?, saldo = ? WHERE id_conta = ?";
            st = connection.prepareStatement(update);
            st.setString(1, c.getNome());
            st.setString(2, c.getCpf());
            st.setString(3, c.getEmail());
            st.setBigDecimal(4, c.getSaldo());
            st.setInt(5, c.getId());
            return st.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(int id) {
        PreparedStatement preparedStatement = null;
        findById(id);
        try {
            String deleteConsulta = "DELETE FROM contas WHERE id_conta = ?";
            preparedStatement = connection.prepareStatement(deleteConsulta);
            preparedStatement.setInt(1, id);
            int result = preparedStatement.executeUpdate();
            if (result == 0) {
                throw new ContasNotFoundException("ID não encontrado");
            }
        } catch (SQLException e) {
            throw new ContasNotFoundException(e.getMessage());
        }
        finally {
            DataBaseConnection.closeStatemant(preparedStatement);
        }
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
