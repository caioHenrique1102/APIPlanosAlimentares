package com.caiodev.planosalimentares.DTO;

import com.caiodev.planosalimentares.Enum.Opcoes;
import com.caiodev.planosalimentares.Enum.Refeicoes;

public record PlanoAlimentarDTO(Refeicoes refeicoes, Opcoes opcoes, int quantidade, String alimentos, Long id){
}
