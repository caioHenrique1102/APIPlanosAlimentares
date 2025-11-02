package com.caiodev.planosalimentares.DTO.Request;

import com.caiodev.planosalimentares.Enum.TipoRefeicoes;

public record CriaRefeicaoDTORequest(String nomeAlimento, TipoRefeicoes tipoRefeicoes) {
}
