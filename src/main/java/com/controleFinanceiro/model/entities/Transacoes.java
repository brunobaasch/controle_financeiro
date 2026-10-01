package com.controleFinanceiro.model.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Transacoes {
    private int id;
    private BigDecimal valor;
    private LocalDate data;
    private int id_conta;
    private TipoTransacao tipo;

}
