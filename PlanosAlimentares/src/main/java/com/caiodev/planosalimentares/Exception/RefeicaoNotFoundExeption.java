package com.caiodev.planosalimentares.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RefeicaoNotFoundExeption extends RuntimeException {
    public RefeicaoNotFoundExeption(String message) {
        super(message);
    }
}
