package com.rnv.letscode.domain;

import com.rnv.letscode.enums.Racas;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.util.Comparator;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rebelde implements Serializable{

    @NotBlank(message = "Informe o nome do rebelde")
    @NotNull(message = "Informe o nome do rebelde")
    private String nome;
    @NotNull(message = "Informe a idade do rebelde")
    private int idade;
    @NotNull(message = "Informe a raça do Rebelde")
    private Racas racas;

    public static Comparator<Rebelde> rebeldeNomeComparator = new Comparator<Rebelde>(){

        @Override
        public int compare(Rebelde r1, Rebelde r2) {
            return r1.getNome().compareToIgnoreCase(r2.getNome());
        }
    };

    public static Comparator<Rebelde> rebeldeIdadeComparator = new Comparator<Rebelde>() {
        @Override
        public int compare(Rebelde r1, Rebelde r2) {
            return r1.getIdade() - r2.getIdade();
        }
    };

    public static Comparator<Rebelde> rebeldeRacaComparator = new Comparator<Rebelde>() {
        @Override
        public int compare(Rebelde r1, Rebelde r2) {
            return r1.getRacas().name().compareToIgnoreCase(r2.getRacas().name());
        }
    };
}
