package com.caiodev.planosalimentares.Model.Entity;

import com.caiodev.planosalimentares.DTO.RefeicaoDTO;
import com.caiodev.planosalimentares.Enum.TipoRefeicoes;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_refeicao")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Refeicao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @OneToMany
    @NotNull
    private List <Alimentos> alimentos = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    private TipoRefeicoes tipoRefeicoes;

    public Refeicao(RefeicaoDTO refeicaoDTO){
        this.alimentos = refeicaoDTO.alimentos();
        this.tipoRefeicoes = refeicaoDTO.tipoRefeicoes();
    }
}
