package com.controleFinanceiro;
import java.math.BigDecimal;
import java.util.*;
import com.controleFinanceiro.model.*;

public class Main {
    public static void main(String[] args) {
        int x = 0;
        Scanner scanner = new Scanner(System.in);
        Conta c1 = new Conta("Bruno");
        c1.creditar(BigDecimal.valueOf(1620));
        System.out.println(c1.getSaldo());
        c1.debitar(new BigDecimal(-50));

        c1.registrarTransacao(BigDecimal.valueOf(10), "Lazer");

        List<Transacao> l = c1.getTransacoes();
        verTransacoes(l);
        System.out.println(c1.getSaldo());

//        while (x!=0) {
//            System.out.println("1- Registrar gasto" +
//                    "2- Ver gastos" +
//                    "0- Sair");
//            x = scanner.nextInt();
//
//            switch (x) {
//                case 1 -> ;
//                case 2 -> ;
//            }
//        }
    }

    public static void verTransacoes(List<Transacao> l) {
        List<Transacao> l1 = new ArrayList<>(l);
        for (Transacao t : l1) {
            System.out.println(t.getValor() + " ; " + t.getData() + " ; " + t.getCategoria());
        }
    }
}