package com.caiodev.planosalimentares.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AlimentoNotFoundExeption extends RuntimeException {
    public AlimentoNotFoundExeption(String message) {
        super(message);
    }
}
