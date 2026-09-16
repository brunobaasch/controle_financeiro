package com.controleFinanceiro;
import java.math.BigDecimal;
import java.util.*;
import com.controleFinanceiro.model.*;

public class Main {
    public static void main(String[] args) {
        int x = 1;
        Scanner scanner = new Scanner(System.in);
        Conta c1 = new Conta("Bruno");
        c1.creditar(BigDecimal.valueOf(2000));

        while (x!=0) {
            System.out.println("1- Registrar gasto" +
                    "\n2- Ver gastos" +
                    "\n0- Sair");
            x = scanner.nextInt();

            switch (x) {
                case 1 -> {
                    scanner.nextLine();
                    System.out.println("Digite o valor do gasto: ");
                    String valor = scanner.nextLine();
                    BigDecimal val = new BigDecimal(valor);
                    System.out.println("Digite a categoria: ");
                    String cat = scanner.nextLine();
                    boolean teste = c1.registrarTransacao(val, cat);
                    System.out.println(teste);
                }
                case 2 -> {
                    List<Transacao> l = new ArrayList<>(c1.getTransacoes());
                    verTransacoes(l);
                }
            }
        }
    }

    public static void verTransacoes(List<Transacao> l) {
        for (Transacao t : l) {
            System.out.println(t.getValor() + " ; " + t.getData() + " ; " + t.getCategoria());
        }
    }
}