package com.directory.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends HttpStatusException {
    // Ошбка выбрасываемая при отсутствии требуемых данных в БД
    public NotFoundException(String message, HttpStatus status) {
        super(message, status);
    }
}
