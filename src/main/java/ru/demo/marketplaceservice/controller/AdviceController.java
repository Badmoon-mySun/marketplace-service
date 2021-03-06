package ru.demo.marketplaceservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import ru.demo.marketplaceservice.exception.NotFoundException;
import ru.demo.marketplaceservice.exception.OrderCreationException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Anvar Khasanov
 * student of ITIS KFU
 */
@RestControllerAdvice
public class AdviceController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public Map<String, String> handleValidationExceptions(BindException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public Map<String, String> handleValidationExceptions(NotFoundException exception) {
        Map<String, String> messages = new HashMap<>();
        messages.put("code", "404");
        messages.put("msg", exception.getMessage());

        return messages;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(OrderCreationException.class)
    public Map<String, String> handleValidationExceptions(OrderCreationException exception) {
        Map<String, String> messages = new HashMap<>();
        messages.put("code", "400");
        messages.put("msg", exception.getMessage());

        return messages;
    }
}
