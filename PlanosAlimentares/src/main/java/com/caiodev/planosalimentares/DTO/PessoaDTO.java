package com.caiodev.planosalimentares.DTO;

import com.caiodev.planosalimentares.Model.Entity.PlanoAlimentar;

public record PessoaDTO(Long id, String nome, String altura, int idade, PlanoAlimentar planoAlimentar){
}
