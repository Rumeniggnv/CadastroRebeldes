package com.rnv.letscode.ic;

import com.rnv.letscode.enums.Racas;
import com.rnv.letscode.domain.Rebelde;
import com.rnv.letscode.utils.FileUtils;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.Cleanup;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Stream;

public class InteligenciaCentral {

    private int idade;
    private int numRaca;

    private static Rebelde rebelde = new Rebelde();

    ArrayList<Rebelde> rebeldes = new ArrayList<>();

    public static final Logger LOGGER = LogManager.getLogger(InteligenciaCentral.class);

    int askIdade() {
        System.out.print("Informe a sua Idade: ");
        try {
            this.idade = new Scanner(System.in).nextInt();
        }
        catch(InputMismatchException e) {
            LOGGER.info("A idade está errada, informe novamente");
            this.askIdade();
        }
        return idade;
    }
    void showRaca() {
        System.out.println("::===================::");
        System.out.println("::       RAÇAS       ::");
        System.out.println("::===================::");
        for ( Racas r : Racas.class.getEnumConstants() ) {
            if (r.name().length() > 4 ) {
                System.out.printf("::   %d - %s      ::\n", r.ordinal(), r.name());
            } else {
                System.out.printf("::   %d - %s        ::\n", r.ordinal(), r.name());
            }
        }
        System.out.println("::===================::");
    }
    Racas askRaca() {
        System.out.print("Informe a sua Raca: ");
        numRaca = new Scanner(System.in).nextInt();
        if ( numRaca < 0 || numRaca >= Racas.values().length ) {
            LOGGER.info("A Raça informada não existe, tente novamente! ");
            askRaca();
        }
        return Racas.values()[numRaca];
    }

    public void novoRebelde(){
        try {
            this.rebeldes = FileUtils.lerArquivo();
        } catch (FileNotFoundException e) {
            InteligenciaCentral.LOGGER.error(":: Arquivo de cadastro de rebeldes inexistente");
        }

        Scanner scan = new Scanner(System.in);
        System.out.print("Informe o seu Nome candidato: ");
        rebelde.setNome( scan.nextLine() );
        rebelde.setIdade( this.askIdade() );

        this.showRaca();
        rebelde.setRacas( this.askRaca() );

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Rebelde>> constraintViolations = validator.validate(rebelde);
        if ( !constraintViolations.isEmpty() ) {
            constraintViolations.forEach(x -> System.out.println(x.getMessage()));
            novoRebelde();
        } else {
            if (this.verificarCandidato()) {
                System.out.println("::          *** Parabéns ***          ::");
                System.out.println(":: Você foi aceito nas tropas rebeldes ::");
                System.out.println(rebelde.toString());

                this.rebeldes.add(rebelde);
                this.rebeldes.sort (Rebelde.rebeldeNomeComparator);

                FileUtils.gravarArquivo(rebeldes);

            } else {
                LOGGER.info(":: Infelizmente você não foi aceito nas tropas ::");
            }
        }
        System.out.println("Pressione <ENTER> para continuar");
        new Scanner(System.in).nextLine();
    }

    boolean verificarCandidato() {
        return new Random().nextBoolean();
    }

}
