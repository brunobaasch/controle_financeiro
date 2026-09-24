package com.controleFinanceiro.exceptions;

public class ValueIsLessZeroException extends Exception {
    public ValueIsLessZeroException() {
        super("Valor é menor do que zero!");
    }

    public ValueIsLessZeroException(String errorMessage) {
        super(errorMessage);
    }
}
