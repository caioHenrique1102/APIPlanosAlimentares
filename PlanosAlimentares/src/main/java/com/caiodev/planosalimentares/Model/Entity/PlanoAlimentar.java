package com.caiodev.planosalimentares.Model.Entity;

import com.caiodev.planosalimentares.Enum.Opcoes;
import com.caiodev.planosalimentares.Enum.Refeicoes;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_plano_alimentar")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlanoAlimentar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String alimentos;
    private int quantidade;
    @Enumerated(EnumType.STRING)
    private Opcoes opcoes;
    @Enumerated(EnumType.STRING)
    private Refeicoes refeicoes;
}
