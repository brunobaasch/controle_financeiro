package com.controleFinanceiro.model;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Arquivo {
    public void escrever(String texto, String arquivo) {
        try (FileWriter fw = new FileWriter(arquivo, true)) {
            BufferedWriter writer = new BufferedWriter(fw);
            writer.write(texto);
            writer.newLine();
            writer.close();
            // importante: "fecha" o arquivo, garantindo que tudo foi salvo
            System.out.println("Arquivo salvo com sucesso!");
        } catch (
                IOException e) {
            System.out.println("Deu erro ao salvar o arquivo: " + e.getMessage());
        }
    }

    public void lerArquivo(String arquivo) {
        {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(arquivo));
                String linha = reader.readLine(); // lê uma linha por vez
                System.out.println("Li do arquivo:\n" + linha);
                linha = reader.readLine();
                while (linha != null) {
                    System.out.println(linha);
                    linha = reader.readLine();
                }
                reader.close();
            } catch (IOException e) {
                System.out.println("Deu erro ao ler o arquivo: " + e.getMessage());
            }
        }
    }

    public ArrayList<String> consultaTransacaoData(String dataIni, String dataFim) {
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

