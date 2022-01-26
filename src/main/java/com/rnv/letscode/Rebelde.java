package com.rnv.letscode;

import com.rnv.letscode.Raca;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
public class Rebelde {
    private String nome;
    private int idade;
    private Raca raca;
}
