package com.controleFinanceiro.model;

import java.math.BigDecimal;

public class Conta {
    private static int contador = 0;
    private int id;
    private String nome;
    private BigDecimal saldo = new BigDecimal(0);

    public Conta(String nome) {
        this.id = ++contador;
        this.nome = nome;
    }

    //metodos
    public boolean debitar(String valor) {
        if (valor.)
    }

    //setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }
    //getters
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }


    public BigDecimal getSaldo() {
        return saldo;
    }

}
