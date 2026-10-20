package com.controleFinanceiro.model.entities;

import java.math.BigDecimal;

public class Contas {
    private int id;
    private String nome;
    private String cpf;
    private String email;
    private BigDecimal saldo = new BigDecimal(0);

    //constructor

    public Contas(String nome, String cpf, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.saldo = BigDecimal.ZERO;
    }

    public Contas(int id, String nome, String cpf, String email, BigDecimal saldo) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.saldo = saldo;
    }

    //setter
    public void setId(int id) {
        this.id = id;
    }

    // getters
    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    // setters
}

