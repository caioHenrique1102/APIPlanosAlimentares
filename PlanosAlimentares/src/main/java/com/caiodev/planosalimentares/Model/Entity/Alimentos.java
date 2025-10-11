package com.caiodev.planosalimentares.Model.Entity;

import com.caiodev.planosalimentares.DTO.AlimentosDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_alimentos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Alimentos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String nome;
    private String quantidade;

    public Alimentos(AlimentosDTO alimentosDTO){
        this.nome  = alimentosDTO.nome();
        this.quantidade = alimentosDTO.quantidade();
    }
}
