package com.controleFinanceiro.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Transacao {
    private static int contador = 0;
    private int id = 0;
    private String categoria;
    private BigDecimal valor;
    private TipoTransacao tipoTransacao;
    private LocalDate data;
    private Conta conta;
    private int contaAssociada;

    public Transacao(BigDecimal valor, String categoria, TipoTransacao tipoTransacao, int contaAssociada) {
        this.valor = valor;
        this.categoria = categoria;
        this.data = LocalDate.now();
        this.id = ++contador;
        this.tipoTransacao = tipoTransacao;
        this.contaAssociada = contaAssociada;
    }

    public Transacao(BigDecimal valor, String categoria, TipoTransacao tipoTransacao, int contaAssociada, LocalDate data) {
        this.valor = valor;
        this.categoria = categoria;
        this.contaAssociada = contaAssociada;
        this.data = data;
        this.id = ++contador;
        this.tipoTransacao = tipoTransacao;
    }

    //metodos

    //setters
    //getters

    public TipoTransacao getTipoTransacao() { return tipoTransacao; }

    public LocalDate getData() {
        return data;
    }

    public int getContaAssociada() {
        return contaAssociada;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getCategoria() {
        return categoria;
    }
}
