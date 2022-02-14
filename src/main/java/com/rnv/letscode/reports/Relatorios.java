package com.rnv.letscode.reports;

import com.rnv.letscode.domain.Rebelde;
import com.rnv.letscode.ic.InteligenciaCentral;
import com.rnv.letscode.utils.FileUtils;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class Relatorios {
    private ArrayList<Rebelde> rebeldes = new ArrayList<Rebelde>();

    public void lerArquivo() {
        try {
            this.rebeldes = FileUtils.lerArquivo();
        } catch (FileNotFoundException e) {
            InteligenciaCentral.LOGGER.error(":: Arquivo de cadastro de rebeldes inexistente");
        }
    }

    public void showRebeldes() {
        System.out.println("::==================================::");
        System.out.println("::        REBELDES ALISTADOS        ::");
        System.out.println("::==================================::");
        for ( Rebelde r : rebeldes) {
            System.out.println(":: " + r.toString());
        }
        System.out.println("::==================================::");

        System.out.println("Pressione <ENTER> para continuar");
        new Scanner(System.in).nextLine();
    }

    public void showHeader() {
        System.out.println("::==================================::");
        System.out.println("::         LISTA DE REBELDES        ::");
        System.out.println("::==================================::");
        System.out.println("::         Escolha uma opção        ::");
        System.out.println("::                                  ::");
        System.out.println("::   [N] Listar Rebeldes p/ Nomes   ::");
        System.out.println("::   [I] Listar Rebeldes p/ Idade   ::");
        System.out.println("::   [R] Listar Rebeldes p/ Raca    ::");
        System.out.println("::   [V] Voltar                     ::");
        System.out.println("::                                  ::");
        System.out.println("::==================================::");
    }
    public void init() {
        this.lerArquivo();
        boolean loop = true;
        while (loop) {
            this.showHeader();
            System.out.print(":: Escolha uma opção: ");
            String op = new Scanner(System.in).next();
            switch (op.toLowerCase()){
                case "n" :
                    this.rebeldes.sort(Rebelde.rebeldeNomeComparator);
                    this.showRebeldes();
                    break;
                case "i" :
                    this.rebeldes.sort(Rebelde.rebeldeIdadeComparator);
                    this.showRebeldes();
                    break;
                case "r" :
                    this.rebeldes.sort(Rebelde.rebeldeRacaComparator);
                    this.showRebeldes();
                    break;
                case "v" :
                    loop = false;
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente");
            }
        }
    }
}
