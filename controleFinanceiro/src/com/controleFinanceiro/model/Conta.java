package com.controleFinanceiro.model;

import java.math.BigDecimal;
import java.util.*;

public class Conta {
    private static int contador = 0;
    private int id;
    private String nome;
    private BigDecimal saldo = new BigDecimal(0);
    private List<Transacao> transacoes = new ArrayList<>();

    public Conta(String nome) {
        this.id = ++contador;
        this.nome = nome;
    }

    //metodos
    public boolean debitar(BigDecimal valorGasto) {
        if (valorGasto.compareTo(saldo) > 0) {
            return false;
        }
        this.saldo = this.saldo.subtract(valorGasto);
        return true;
    }

    public boolean creditar(BigDecimal valorGasto) {
        this.saldo = this.saldo.add(valorGasto);
        return true;
    }

    public void adicionarNaLista (Transacao t) {
        this.transacoes.add(t);
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

    public List<Transacao> getTransacoes() {
        return transacoes;
    }

    public String getNome() {
        return nome;
    }


    public BigDecimal getSaldo() {
        return saldo;
    }

}
