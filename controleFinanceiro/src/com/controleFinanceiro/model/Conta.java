package com.controleFinanceiro.model;

import java.math.BigDecimal;
import java.util.*;

public class Conta {
    private static int contador = 0;
    private int id;
    private String nome;
    private BigDecimal saldo = new BigDecimal(0);
    private List<Transacao> transacoes = new ArrayList<>();
    private Arquivo arq = new Arquivo();

    public Conta(String nome) {
        this.id = ++contador;
        this.nome = nome;
        escreveConta(nome);
    }

    //metodos

    public boolean debitar(BigDecimal valorGasto) {
        if (validarSolicitacao(valorGasto, TipoTransacao.DESPESA)) {
            this.saldo = this.saldo.subtract(valorGasto);
            return true;
        }
        return false;
    }
    public boolean creditar(BigDecimal valorGasto) {
        if (validarSolicitacao(valorGasto, TipoTransacao.RECEITA)) {
            this.saldo = this.saldo.add(valorGasto);
            return true;
        }
        return false;
    }

    protected void adicionarNaLista (Transacao t) {
        this.transacoes.add(t);
    }

    public boolean registrarTransacao (BigDecimal valor, String categoria, TipoTransacao tipo) {
        if (validarSolicitacao(valor, tipo)) {
            Transacao t = new Transacao(valor, categoria, tipo, getId());
            adicionarNaLista(t);
            escreveTransacao(t);
            if (tipo == TipoTransacao.DESPESA) {
                return debitar(valor);
            }else if(tipo == TipoTransacao.RECEITA) {
                return creditar(valor);
            }
        }
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

    public void escreveTransacao(Transacao t) {
        String texto = t.getValor() +";"+ t.getCategoria() +";"+ t.getTipoTransacao() +";"+ t.getData() + ";" + t.getContaAssociada();
        arq.escrever(texto, "dadosTransacao.csv");
    }

    public void escreveConta(String nome) {
        String texto = nome +";"+ this.id;
        arq.escrever(texto, "dadosContas.csv");
    }

    //setters
    //getters

    public List<Transacao> getTransacoes() {
        return new ArrayList<>(transacoes);
    }
    public int getId() {return id;}
    public BigDecimal getSaldo() {
        return saldo;
    }

}
