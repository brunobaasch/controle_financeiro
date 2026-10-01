package com.controleFinanceiro;

import com.controleFinanceiro.connection.DataBaseConnection;
import com.controleFinanceiro.dao.impl.ContasDaoImpl;
import com.controleFinanceiro.model.entities.Contas;

import java.sql.Connection;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        DataBaseConnection db = new DataBaseConnection();
        Connection d = db.getConnection();
//        List<Contas> c;
        ContasDaoImpl contasDao = new ContasDaoImpl(d);
//        c = contasDao.getAll();
//        for (Contas co : c) {
//            System.out.println(co.getNome());
//        }

        Contas c = new Contas(2, "Julio", "9876543210", "julio@dev.com");
        contasDao.insert(c);
   }
}