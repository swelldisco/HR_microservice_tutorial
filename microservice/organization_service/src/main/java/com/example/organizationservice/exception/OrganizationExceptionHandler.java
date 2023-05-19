package com.example.organizationservice.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class OrganizationExceptionHandler extends ResponseEntityExceptionHandler{
    
    @ExceptionHandler(OrganizationNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleOrganizationNotFoundException(OrganizationNotFoundException exception, WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(
            LocalDateTime.now(), 
            exception.getMessage(), 
            webRequest.getDescription(false), "ORGANIZATION_NOT_FOUND");
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RepositoryEmptyException.class)
    public ResponseEntity<ErrorDetails> handleRepositoryEmptyException(RepositoryEmptyException exception, WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(
            LocalDateTime.now(), 
            exception.getMessage(), 
            webRequest.getDescription(false), "REPOSITORY_EMPTY");
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGlobalException(Exception exception, WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(
            LocalDateTime.now(), 
            exception.getMessage(), 
            webRequest.getDescription(false), "INTERNAL_SERVER_ERROR");
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> validationErrors = new HashMap<>();
        List<ObjectError> errorList = ex.getBindingResult().getAllErrors();
                errorList.forEach(e -> {
                    String fieldName = ((FieldError)e).getField();
                    String message = e.getDefaultMessage();
                    validationErrors.put(fieldName, message);
            });
        return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
    }
}
