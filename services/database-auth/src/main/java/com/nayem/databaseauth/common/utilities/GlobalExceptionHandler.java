package com.nayem.databaseauth.common.utilities;

import com.nayem.databaseauth.common.exceptions.EnumNotFoundException;
import com.nayem.databaseauth.common.exceptions.NotFoundException;
import com.nayem.databaseauth.common.exceptions.NullPasswordException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    //Database Related Exceptions Handler
    /*Log the exception to capture details for debugging while ensuring sensitive data is not exposed in the response.*/
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException dataIntegrityViolationException) {
        Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
        logger.error("Database error occurred: {}", dataIntegrityViolationException.getMessage(), dataIntegrityViolationException);
        String message = "An error occurred while processing your request. Please check your input and try again.";
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EnumNotFoundException.class)
    public ResponseEntity<String> handleEnumNotFoundException(EnumNotFoundException enumNotFoundException) {
        return new ResponseEntity<>(enumNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
    }

    //Generic Exceptions Handler
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception exception) {
        return new ResponseEntity<>("An unexpected error occurred: " + exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //Bean Validation Related Exceptions Handler
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidExceptions(MethodArgumentNotValidException methodArgumentNotValidException) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : methodArgumentNotValidException.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(NotFoundException notFoundException) {
        return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NullPasswordException.class)
    public ResponseEntity<String> handleNullPasswordException(NullPasswordException nullPasswordException) {
        return new ResponseEntity<>(nullPasswordException.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
