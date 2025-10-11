package com.caiodev.planosalimentares.DTO;

import com.caiodev.planosalimentares.Enum.TipoRefeicoes;
import com.caiodev.planosalimentares.Model.Entity.Alimentos;

import java.util.List;

public record RefeicaoDTO(List<Alimentos> alimentos, TipoRefeicoes tipoRefeicoes) {
}
