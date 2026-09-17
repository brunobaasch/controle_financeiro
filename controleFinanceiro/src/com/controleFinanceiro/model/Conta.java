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
        }else if(valorGasto.compareTo(BigDecimal.ZERO) < 0){
            return false;
        }
        this.saldo = this.saldo.subtract(valorGasto);
        return true;
    }

    public void creditar(BigDecimal valorGasto) {
        this.saldo = this.saldo.add(valorGasto);
    }

    protected void adicionarNaLista (Transacao t) {
        this.transacoes.add(t);
    }

    public boolean registrarTransacao (BigDecimal valor, String categoria, TipoTransacao tipo) {
        if (tipo == TipoTransacao.DESPESA) {
            if (debitar(valor)) {
                Transacao t = new Transacao(valor, categoria, tipo);
                adicionarNaLista(t);
                return true;
            }
        }else if (tipo == TipoTransacao.RECEITA) {
            Transacao t = new Transacao(valor, categoria, tipo);
            creditar(valor);
            adicionarNaLista(t);
            return true;
        }
        return false;
    }

    //setters

    public void setNome(String nome) {
        this.nome = nome;
    }

    //getters
    public int getId() {
        return id;
    }

    public List<Transacao> getTransacoes() {
        return new ArrayList<>(transacoes);
    }

    public String getNome() {
        return nome;
    }


    public BigDecimal getSaldo() {
        return saldo;
    }

}
