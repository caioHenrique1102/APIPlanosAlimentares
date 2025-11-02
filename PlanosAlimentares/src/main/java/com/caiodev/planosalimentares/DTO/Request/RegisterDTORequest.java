package com.caiodev.planosalimentares.DTO.Request;

import jakarta.validation.constraints.NotEmpty;

public record RegisterDTORequest(@NotEmpty(message = "Usuario é obrigatório") String usuario,
                                 @NotEmpty(message = "Senha é obrigatório") String senha){

}

