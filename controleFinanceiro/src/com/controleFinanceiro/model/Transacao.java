package com.controleFinanceiro.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Transacao {
    private static int contador = 0;
    private int id = 0;
    private String categoria;
    private BigDecimal valor;
//  private TipoTransacao tipo;
    private LocalDate data;
    private String descricao;

    public Transacao(BigDecimal valor, String categoria, Conta c) {
        this.valor = valor;
        this.categoria = categoria;
        this.id = ++contador;
        this.data = LocalDate.now();
        debitarValor(c);
    }

    //metodos
    public void debitarValor(Conta c) {
        c.debitar(this.valor);
    }

    //setters

    //getters
    public LocalDate getData() {
        return data;
    }

    public BigDecimal getValor() {
        return valor;
    }
}
