package com.controleFinanceiro.model.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Transacoes {
    private int id;
    private BigDecimal valor;
    private LocalDate data;
    private int id_conta;
    private TipoTransacao tipo;

    public Transacoes(int id, BigDecimal valor, LocalDate data, int id_conta, TipoTransacao tipo) {
        this.id = id;
        this.valor = valor;
        this.data = data;
        this.id_conta = id_conta;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public LocalDate getData() {
        return data;
    }

    public int getId_conta() {
        return id_conta;
    }

    public TipoTransacao getTipo() {
        return tipo;
    }
}
