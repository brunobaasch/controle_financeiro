package com.controleFinanceiro.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Transacao {
    private final String categoria;
    private final BigDecimal valor;
    private final TipoTransacao tipoTransacao;
    private final LocalDate data;
    private Conta conta;
    private final int contaAssociada;

    public Transacao(BigDecimal valor, String categoria, TipoTransacao tipoTransacao, int contaAssociada) {
        this.valor = valor;
        this.categoria = categoria;
        this.data = LocalDate.now();
        this.tipoTransacao = tipoTransacao;
        this.contaAssociada = contaAssociada;
    }

    public Transacao(BigDecimal valor, String categoria, TipoTransacao tipoTransacao, int contaAssociada, LocalDate data) {
        this.valor = valor;
        this.categoria = categoria;
        this.contaAssociada = contaAssociada;
        this.data = data;
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
