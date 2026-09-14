package com.controleFinanceiro;
import java.math.BigDecimal;
import java.util.*;
import com.controleFinanceiro.model.*;

public class Main {
    public static void main(String[] args) {
        int x = 0;
        Scanner scanner = new Scanner(System.in);
        Conta c1 = new Conta("Bruno");
        c1.setSaldo(BigDecimal.valueOf(1620));
        System.out.println(c1.getSaldo());

        Transacao t1 = new Transacao(BigDecimal.valueOf(1000), "Lazer", c1);
        Transacao t2 = new Transacao(BigDecimal.valueOf(300), "Energia", c1);

        System.out.println(c1.getSaldo());

        List<Transacao> l = c1.getTransacoes();

        for (Transacao t : l) {
            System.out.println(t.getValor() + " ; " + t.getData() + " ; " + t.getCategoria());
        }

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
}