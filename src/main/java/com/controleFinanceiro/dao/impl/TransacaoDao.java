package com.controleFinanceiro.dao.impl;

import com.controleFinanceiro.connection.DataBaseConnection;
import com.controleFinanceiro.dao.Dao;
import com.controleFinanceiro.exceptions.ContasNotFoundException;
import com.controleFinanceiro.exceptions.TransacaoNotFoundException;
import com.controleFinanceiro.model.entities.Contas;
import com.controleFinanceiro.model.entities.TipoTransacao;
import com.controleFinanceiro.model.entities.Transacoes;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransacaoDao implements Dao<Transacoes> {
    private Connection connection;

    @Override
    public Transacoes findById(int id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String sqlConsultaId = "SELECT * FROM transacoes WHERE id_transacao = ?";
            preparedStatement = connection.prepareStatement(sqlConsultaId);
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return getTransacao(resultSet);
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
    public List<Transacoes> getAll() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Transacoes> lista = new ArrayList<>();

        try {
            String sqlConsultaId = "SELECT * FROM transacoes";
            preparedStatement = connection.prepareStatement(sqlConsultaId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                lista.add(getTransacao(resultSet));
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
    public void insert(Transacoes transacao) {
        PreparedStatement pstm = null;

        try {
            pstm = connection.prepareStatement("INSERT INTO transacoes(id_transacao, valor, data, id_conta, tipo) VALUES(?, ?, ?, ?, ?)");
            pstm.setInt(1, transacao.getId());
            pstm.setBigDecimal(2, transacao.getValor());
            pstm.setObject(3, transacao.getData());
            pstm.setInt(4, transacao.getId_conta());
            pstm.setObject(5, transacao.getTipo());
            int resultado = pstm.executeUpdate();
            System.out.println(resultado + " linhas alteradas");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            DataBaseConnection.closeStatemant(pstm);
        }
    }

    @Override
    public int update(Transacoes t) {
        PreparedStatement st = null;
        findById(t.getId());
        try {
            String update = "UPDATE transacoes SET valor = ?, data = ?, tipo  = ? WHERE id_transacao = ?";
            st = connection.prepareStatement(update);
            st.setBigDecimal(1, t.getValor());
            st.setObject(2, t.getData());
            st.setObject(3, t.getTipo());
            st.setInt(4, t.getId());
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
            String deleteConsulta = "DELETE FROM transacoes WHERE id_transacao = ?";
            preparedStatement = connection.prepareStatement(deleteConsulta);
            preparedStatement.setInt(1, id);
            int result = preparedStatement.executeUpdate();
            if (result == 0) {
                throw new TransacaoNotFoundException("ID não encontrado");
            }
        } catch (SQLException e) {
            throw new TransacaoNotFoundException(e.getMessage());
        }
        finally {
            DataBaseConnection.closeStatemant(preparedStatement);
        }
    }

    private static Transacoes getTransacao(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id_transacao");
        BigDecimal valor = resultSet.getBigDecimal("valor");
        LocalDate data = resultSet.getObject("data", LocalDate.class);
        int idconta = resultSet.getInt("id_conta");
        TipoTransacao tipo = resultSet.getObject("tipo", TipoTransacao.class);
        return new Transacoes(id, valor, data, idconta, tipo);
    }
}
