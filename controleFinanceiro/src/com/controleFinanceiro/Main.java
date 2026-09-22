package com.controleFinanceiro;
import java.awt.image.AreaAveragingScaleFilter;
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
                    String valor = scanner.nextLine();
                    BigDecimal val = new BigDecimal(valor);
                    System.out.println("Digite a categoria: ");
                    String cat = scanner.nextLine();
                    boolean teste = c1.registrarTransacao(val, cat, TipoTransacao.RECEITA);
                    System.out.println(teste);
                }
                case 3 -> {
                    List<Transacao> l = new ArrayList<>(c1.getTransacoes());
                    verTransacoes(l);
                }

                case 4 -> System.out.println(c1.getSaldo());
                case 5 -> arq.lerArquivo("dadosTransacao.csv");
                case 6 -> {
                    scanner.nextLine();
                    System.out.println("Data inicio consulta: ");
                    String ini = scanner.nextLine();
                    System.out.println("Data fim consulta: ");
                    String fim = scanner.nextLine();
                    ArrayList<String> l = consultaTransacaoData(ini, fim);;
                    for (String t:l) {
                        System.out.println(t);
                    }
                }
            }
        }
    }

    public static void verTransacoes(List<Transacao> l) {
        for (Transacao t : l) {
            System.out.println(t.getValor() + " ; "
                    + t.getData() + " ; " + t.getCategoria() + " ; "
                    + t.getTipoTransacao() + " ; " + t.getContaAssociada());
        }
    }

    public static ArrayList<String> consultaTransacaoData(String dataIni, String dataFim) {
        ArrayList<String> transacoes = new ArrayList<>();
        try {
            LocalDate ini = LocalDate.parse(dataIni);
            LocalDate fim = LocalDate.parse(dataFim);

            BufferedReader reader = new BufferedReader(new FileReader("dadosTransacao.csv"));
            String linha = reader.readLine();
            while (linha != null) {
                String[] t = linha.split(";");
                LocalDate data = LocalDate.parse(t[3]);
                if (!data.isBefore(ini) && !data.isAfter(fim)) {
                    transacoes.add(linha);
                }
                linha = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Deu erro ao ler o arquivo: " + e.getMessage());
        }
        return transacoes;
    }
}