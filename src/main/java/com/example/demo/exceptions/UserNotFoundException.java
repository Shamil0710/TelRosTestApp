package com.example.demo.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Кастомное исключение необходимое для логирования и потенциально дополнительной логики
 */
public class UserNotFoundException extends RuntimeException{
    private static final Logger log = LoggerFactory.getLogger(UserNotFoundException.class);
    public UserNotFoundException(String message) {
        super(message);
        log.warn("Пользователь с с ID {} не найден", message);
    }
}
