package com.controleFinanceiro.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Transacao {
    private int id = 1;
    private String categoria;
    private BigDecimal valor;
//  private TipoTransacao tipo;
    private LocalDate data;
    private String descricao;

    public Transacao(BigDecimal valor, String categoria, String descricao) {
        this.valor = valor;
        this.categoria = categoria;
        this.descricao = descricao;
        this.id = this.id++;
        this.data = LocalDate.now();
    }
}
