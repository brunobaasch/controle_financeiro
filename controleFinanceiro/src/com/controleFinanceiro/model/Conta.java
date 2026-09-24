package com.controleFinanceiro.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import com.controleFinanceiro.exceptions.*;

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
        escreveConta();
    }

    //metodos

    public void debitar(BigDecimal valorGasto) {
        this.saldo = this.saldo.subtract(valorGasto);
    }
    public void creditar(BigDecimal valorGasto) {
        this.saldo = this.saldo.add(valorGasto);
    }

    protected void adicionarNaLista (Transacao t) {
        this.transacoes.add(t);
    }

    public void registrarTransacao (BigDecimal valor, String categoria, TipoTransacao tipo) throws ValueIsLessZeroException, ValueIsBiggerThanBalanceException {
        if (validarSolicitacao(valor, tipo)) {
            Transacao t = new Transacao(valor, categoria, tipo, getId());
            adicionarNaLista(t);
            escreveTransacao(t);
            if (tipo == TipoTransacao.DESPESA) {
                debitar(valor);
            } else if (tipo == TipoTransacao.RECEITA) {
                creditar(valor);
            }
        }
    }

    public boolean validarSolicitacao (BigDecimal valor, TipoTransacao tipo) throws ValueIsBiggerThanBalanceException, ValueIsLessZeroException {
        if (valor.compareTo(BigDecimal.ZERO) < 0) {
            throw new ValueIsLessZeroException();
        }
        if(tipo == TipoTransacao.DESPESA && valor.compareTo(this.saldo) > 0) {
            throw new ValueIsBiggerThanBalanceException();
        }
        return true;
    }

    public void escreveTransacao(Transacao t) {
        String texto = t.getValor() +";"+ t.getCategoria() +";"+ t.getTipoTransacao() +";"+ t.getData() + ";" + t.getContaAssociada();
        arq.escrever(texto, "dadosTransacao.csv");
    }

    public void escreveConta() {
        boolean contaCadastrada = false;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("dadosContas.csv"));
            String linha = reader.readLine(); // lê uma linha por vez
            while (linha != null) {
                String[] t = linha.split(";");
                int idT = Integer.parseInt(t[1]);
                if (idT == this.id) {
                    contaCadastrada = true;
                }
                linha = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Deu erro ao ler o arquivo: " + e.getMessage());
        }
        if (!contaCadastrada) {
            String texto = this.nome +";"+ this.id;
            arq.escrever(texto, "dadosContas.csv");
        }
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
