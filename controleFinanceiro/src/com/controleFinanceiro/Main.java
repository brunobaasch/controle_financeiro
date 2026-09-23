package com.controleFinanceiro;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import com.controleFinanceiro.model.*;

public class Main {
    public static void main(String[] args) {
        Arquivo arq = new Arquivo();
        int x = -1;
        int y = -1;
        Scanner scanner = new Scanner(System.in);
        Conta c1 = new Conta("Bruno");
        Conta c2 = new Conta("Eduardo");
        c1.creditar(BigDecimal.valueOf(2000));


        while (x != 0) {
            System.out.println("""
                    1- Registrar saída
                    2- Registrar entrada
                    3- Ver transacoes
                    4- Ver saldo
                    5- Ler arquivo
                    6- Consultar transacoes por data
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
                        boolean teste = c1.registrarTransacao(val, cat, TipoTransacao.DESPESA);
                        System.out.println(teste);
                    } catch (NumberFormatException e) {
                        System.out.println("Digite um valor númerico!");
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
                        boolean teste = c1.registrarTransacao(val, cat, TipoTransacao.RECEITA);
                        System.out.println(teste);
                    } catch (NumberFormatException e){
                        System.out.println("Digite um valor númerico!");
                    }
                }
                case 3 -> {
                    ArrayList<Transacao> transacoes = arq.transacoesSalva();
                    for (Transacao t:transacoes) {
                        System.out.println(t.getValor() + " " + t.getCategoria() + " " + t.getTipoTransacao() + " " + t.getData());
                    }
                }

                case 4 -> System.out.println(c1.getSaldo());
                case 5 -> arq.lerArquivo("dadosTransacao.csv");
                case 6 -> {
                    scanner.nextLine();
                    System.out.println("Data inicio consulta: ");
                    String ini = scanner.nextLine();
                    System.out.println("Data fim consulta: ");
                    String fim = scanner.nextLine();
                    ArrayList<Transacao> l = arq.consultaTransacaoData(ini, fim);
                    for (Transacao t:l) {
                        System.out.println(t.getValor() + " " + t.getCategoria() + " " + t.getTipoTransacao() + " " + t.getData());
                    }
                }
            }
        }
    }

//    public static void verTransacoes(List<Transacao> l) {
//        for (Transacao t : l) {
//            System.out.println(t.getValor() + " ; "
//                    + t.getData() + " ; " + t.getCategoria() + " ; "
//                    + t.getTipoTransacao() + " ; " + t.getContaAssociada());
//        }
//    }
}