package com.caiodev.planosalimentares.Model.Entity;

import com.caiodev.planosalimentares.Enum.TipoRefeicoes;
import jakarta.persistence.*;
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
    @ManyToMany
    private List <Alimentos> alimentos = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private TipoRefeicoes tipoRefeicoes;


}
