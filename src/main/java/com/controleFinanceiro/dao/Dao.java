package com.controleFinanceiro.dao;

import java.util.List;

public interface    Dao<T> {

    T findById(int id);
    List<T> getAll();
    void insert(T t);
    int update(T t);
    void deleteById(int id);
}
