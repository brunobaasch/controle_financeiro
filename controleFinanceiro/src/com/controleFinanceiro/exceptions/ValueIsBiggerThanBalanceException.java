package com.controleFinanceiro.exceptions;

public class ValueIsBiggerThanBalanceException extends Exception{
    public ValueIsBiggerThanBalanceException() {
        super("Valor é maior que saldo!");
    }

    public ValueIsBiggerThanBalanceException(String message) {
        super(message);
    }
}
