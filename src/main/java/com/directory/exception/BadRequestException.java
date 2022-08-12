package com.directory.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends HttpStatusException {
    // Ошибка выбрасваемая при ошибочном запросе
    public BadRequestException(String message, HttpStatus status) {
        super(message, status);
    }
}
