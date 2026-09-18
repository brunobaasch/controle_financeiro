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
        if (!validarSolicitacao(valorGasto, TipoTransacao.DESPESA)) {
            return false;
        }
        this.saldo = this.saldo.subtract(valorGasto);
        return true;
    }

    public boolean creditar(BigDecimal valorGasto) {
        if (!validarSolicitacao(valorGasto, TipoTransacao.RECEITA)) {
            return false;
        }
        this.saldo = this.saldo.add(valorGasto);
        return true;
    }

    protected void adicionarNaLista (Transacao t) {
        this.transacoes.add(t);
    }

    public boolean registrarTransacao (BigDecimal valor, String categoria, TipoTransacao tipo) {
        if (validarSolicitacao(valor, tipo)) {
            Transacao t = new Transacao(valor, categoria, tipo);
            adicionarNaLista(t);
            if (tipo == TipoTransacao.DESPESA) {
                debitar(valor);
            }else if(tipo == TipoTransacao.RECEITA) {
                creditar(valor);
            }
            return true;
        }
//        if (tipo == TipoTransacao.DESPESA) {
//            if (debitar(valor)) {
//                adicionarNaLista(t);
//                return true;
//            }
//        }else if (tipo == TipoTransacao.RECEITA) {
//            if (creditar(valor)) {
//                adicionarNaLista(t);
//                return true;
//            }
//        }
        return false;
    }

    public boolean validarSolicitacao (BigDecimal valor, TipoTransacao tipo) {
        if (tipo == TipoTransacao.DESPESA) {
            return valor.compareTo(this.saldo) <= 0 && valor.compareTo(BigDecimal.ZERO) >= 0;
        }else if(tipo == TipoTransacao.RECEITA) {
            return valor.compareTo(BigDecimal.ZERO) >= 0;
        }
        return false;
    }

//    public boolean valorDisponivel(BigDecimal valor) {
//        if (valor.compareTo(saldo) > 0) {
//            return false;
//        }
//        return true;
//    }
//
//    public boolean valorMajorQueZero(BigDecimal valor) {
//        if (valor.compareTo(BigDecimal.ZERO) < 0) {
//            return false;
//        }
//        return true;
//    }


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
