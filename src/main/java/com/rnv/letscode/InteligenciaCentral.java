package com.rnv.letscode;

import lombok.Cleanup;

import java.io.*;
import java.nio.file.Files;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Stream;

public class InteligenciaCentral {

    private int idade;
    private int numRaca;

    int askIdade() {
        System.out.print("Informe a sua Idade: ");
        try {
            this.idade = new Scanner(System.in).nextInt();
        }
        catch(InputMismatchException e) {
            System.out.println("A idade está errada, informe novamente");
            this.askIdade();
        }
        return idade;
    }
    void showRaca() {
        System.out.println("::===================::");
        System.out.println("::       RAÇAS       ::");
        System.out.println("::===================::");
        for ( Raca r : Raca.class.getEnumConstants() ) {
            if (r.name().length() > 4 ) {
                System.out.printf("::   %d - %s      ::\n", r.ordinal(), r.name());
            } else {
                System.out.printf("::   %d - %s        ::\n", r.ordinal(), r.name());
            }
        }
        System.out.println("::===================::");
    }
    Raca askRaca() {
        System.out.print("Informe a sua Raca: ");
        numRaca = new Scanner(System.in).nextInt();
        if ( numRaca < 0 || numRaca >= Raca.values().length ) {
            System.out.println("A Raça informada não existe, tente novamente! ");
            askRaca();
        }
        return Raca.values()[numRaca];
    }

    public void novoRebelde(){
        Rebelde rebelde = new Rebelde();
        Scanner scan = new Scanner(System.in);
        System.out.print("Informe o seu Nome candidato: ");
        rebelde.setNome( scan.nextLine() );
        rebelde.setIdade( this.askIdade() );

        this.showRaca();
        rebelde.setRaca( this.askRaca() );

        if ( this.verificarCandidato() ){
            System.out.println("::          *** Parabéns ***          ::");
            System.out.println(":: Você foi aceito nas tropas rebeldes ::");
            System.out.println(rebelde.toString());
            try {
                this.cadastrarRebelde(rebelde);
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println(":: Infelizmente você não foi aceito nas tropas ::");
        }

        System.out.println("Pressione <ENTER> para continuar");
        new Scanner(System.in).nextLine();

    }

    boolean verificarCandidato() {
        return new Random().nextBoolean();
    }

    void cadastrarRebelde(Rebelde rebelde) throws FileNotFoundException, UnsupportedEncodingException, IOException {
        //@Cleanup PrintWriter writer = new PrintWriter("ListaRebeldes.txt", "UTF-8");
        File arquivo = new File("ListaRebeldes.txt");
        if ( !arquivo.exists() ) {
            arquivo.createNewFile();
        }
        FileReader reader = new FileReader(arquivo);
        BufferedReader br = new BufferedReader(reader);
        long linhas;// = br.read()lines().count();
        try (Stream<String> lines = Files.lines(arquivo.toPath())) {
            linhas = lines.count();
        }

        if ( arquivo.exists() && linhas != 0) {
            @Cleanup FileWriter fw = new FileWriter( arquivo, true );
            @Cleanup BufferedWriter bw = new BufferedWriter( fw );
            bw.write(":: " + (linhas-1) + " - " + rebelde.toString());
            bw.newLine();
        } else {
            @Cleanup PrintWriter writer = new PrintWriter("ListaRebeldes.txt", "UTF-8");
            writer.println(":: Lista de Rebeldes ::\n");
            writer.println(":: 1 - " + rebelde.toString());
        }


    }


}
