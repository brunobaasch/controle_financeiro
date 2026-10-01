package com.controleFinanceiro;
import com.controleFinanceiro.connection.DataBaseConnection;
import com.controleFinanceiro.dao.impl.ContasDaoImpl;

import java.sql.Connection;

public class MainDelete {
    public static void main(String[] args) {
        DataBaseConnection db = new DataBaseConnection();
        Connection d = db.getConnection();
        ContasDaoImpl c = new ContasDaoImpl(d);
        c.deleteById(2);
    }
}
