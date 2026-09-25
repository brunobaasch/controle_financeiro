package com.controleFinanceiro;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import com.controleFinanceiro.exceptions.ValueIsBiggerThanBalanceException;
import com.controleFinanceiro.exceptions.ValueIsLessZeroException;
import com.controleFinanceiro.model.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Conta c1 = new Conta("Bruno");
        Conta c2 = new Conta("Eduardo");
        menu(c1);
    }

    public static void menu(Conta conta) {
        Scanner scanner = new Scanner(System.in);
        Arquivo arq = new Arquivo();
        int x = -1;
        int y = -1;
        while (x != 0) {
            System.out.println("""
                    1- Registrar saída
                    2- Registrar entrada
                    3- Ver transacoes
                    4- Ver saldo
                    5- Ler arquivo 
                    0- Sair""");
            try {
                x = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Digite um número!");
                x = -1;
                scanner.next();
            }
            switch (x) {
                case 1 -> {
                    scanner.nextLine();
                    System.out.println("Digite o valor do gasto: ");
                    try {
                        String valor = scanner.nextLine();
                        BigDecimal val = new BigDecimal(valor);
                        System.out.println("Digite a categoria: ");
                        String cat = scanner.nextLine();
                        conta.registrarTransacao(val, cat, TipoTransacao.DESPESA);
                    } catch (NumberFormatException e) {
                        System.out.println("Digite um valor númerico!");
                    } catch (ValueIsLessZeroException e) {
                        System.out.println("Valor menor que zero");
                    } catch (ValueIsBiggerThanBalanceException e) {
                        System.out.println("Valor maior que saldo");
                    }
                }
                case 2 -> {
                    scanner.nextLine();
                    System.out.println("Digite o valor da entrada: ");
                    try {
                        String valor = scanner.nextLine();
                        BigDecimal val = new BigDecimal(valor);
                        System.out.println("Digite a categoria: ");
                        String cat = scanner.nextLine();
                        conta.registrarTransacao(val, cat, TipoTransacao.RECEITA);
                    } catch (NumberFormatException e) {
                        System.out.println("Digite um valor númerico!");
                    } catch (ValueIsLessZeroException e) {
                        System.out.println("Valor menor que zero");
                    } catch (ValueIsBiggerThanBalanceException e) {
                        System.out.println("Valor maior que saldo");
                    }
                }
                case 3 -> {
                    ArrayList<Transacao> transacoes = arq.transacoesSalva();
                    for (Transacao t : transacoes) {
                        System.out.println(t.getValor() + " " + t.getCategoria() + " " + t.getTipoTransacao() + " " + t.getData());
                    }
                }

                case 4 -> System.out.println(conta.getSaldo());
                case 5 -> arq.lerArquivo("dadosTransacao.csv");
            }
        }
    }
}