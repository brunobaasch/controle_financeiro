package com.controleFinanceiro;

import com.controleFinanceiro.connection.DataBaseConnection;
import com.controleFinanceiro.dao.impl.ContasDaoImpl;
import com.controleFinanceiro.model.entities.Contas;

import javax.xml.crypto.Data;
import java.sql.Connection;

public class MainUpdate {
    public static void main(String[] args) {
        DataBaseConnection db = DataBaseConnection.getInstance();
        Connection d = db.getConnection();
        ContasDaoImpl c = new ContasDaoImpl(d);
        Contas acc = new Contas(2, "Julio", "9876543210", "julio@dev.com");
        c.update(acc);
    }
}
