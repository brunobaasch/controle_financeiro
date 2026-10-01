package com.controleFinanceiro.dao.impl;

import com.controleFinanceiro.dao.Dao;
import com.controleFinanceiro.model.entities.Transacoes;

import java.util.List;

public class TransacaoDao implements Dao<Transacoes> {

    @Override
    public Transacoes findById(int id) {
        return null;
    }

    @Override
    public List<Transacoes> getAll() {
        return List.of();
    }

    @Override
    public void insert(Transacoes transacao) {

    }

    @Override
    public void update(Transacoes transacao, String[] params) {

    }

    @Override
    public void delete(Transacoes transacoes) {

    }
}
