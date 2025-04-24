package com.examly.springapp.exception;

import java.util.HashMap;
import java.util.*;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
   @ExceptionHandler(MethodArgumentNotValidException.class)
   public ResponseEntity<?> handleValidationErrors(MethodArgumentNotValidException e) {
       List<FieldError> errors = e.getBindingResult().getFieldErrors();
       Map<String, String> map = new HashMap<>();
       for(FieldError err: errors){
           map.put(err.getField(), err.getDefaultMessage());
       }
       return ResponseEntity.status(400).body(map.toString());
   }

  @ExceptionHandler(NoUserFoundException.class)
 public ResponseEntity<?> NoUserFound(NoUserFoundException e){
    return ResponseEntity.status(404).body(e.getMessage());
 }
 @ExceptionHandler(IncorrectEmailOrPasswordException.class)
 public ResponseEntity<?> IncorrectEmailOrPassword(IncorrectEmailOrPasswordException e){
    return ResponseEntity.status(404).body(e.getMessage());
 }
}
