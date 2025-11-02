package com.caiodev.planosalimentares.DTO.Request;

import jakarta.validation.constraints.NotEmpty;

public record UsuarioDTORequest(@NotEmpty(message = "Usuario é obrigatorio") String usuario,
                                @NotEmpty(message = "Senha é obrigatoria") String senha){
}
