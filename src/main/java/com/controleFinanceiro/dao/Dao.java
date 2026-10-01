package com.controleFinanceiro.dao;

import java.util.List;

public interface    Dao<T> {

    T findById(int id);
    List<T> getAll();
    void insert(T t);
    void update(T t, String[] params);
    void deleteById(int id);
}
