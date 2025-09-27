package com.caiodev.planosalimentares.Model.Entity;

import com.caiodev.planosalimentares.DTO.PessoaDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_pessoa")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String altura;
    private int idade;
    @OneToOne
    private PlanoAlimentar planoAlimentar;
    
    public Pessoa(PessoaDTO pessoaDTO){
        this.altura = pessoaDTO.altura();
        this.nome = pessoaDTO.nome();
        this.idade = pessoaDTO.idade();
        this.planoAlimentar = pessoaDTO.planoAlimentar();
    }
}
