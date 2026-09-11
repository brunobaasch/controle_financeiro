package com.controleFinanceiro.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Transacao {
    private int id;
    private String contaAssociada;
    private String categoria;
    private BigDecimal valor;
    private TipoTransacao tipo;
    private LocalDate data;
    private String descricao;
}
