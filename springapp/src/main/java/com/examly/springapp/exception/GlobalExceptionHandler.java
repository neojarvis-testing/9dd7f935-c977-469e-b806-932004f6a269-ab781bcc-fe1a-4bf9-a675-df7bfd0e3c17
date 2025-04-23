package com.examly.springapp.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(NoUserFoundException.class)
 public ResponseEntity<?> NoUserFound(NoUserFoundException e){
    return ResponseEntity.status(404).body(e.getMessage());
 }
 @ExceptionHandler(IncorrectEmailOrPasswordException.class)
 public ResponseEntity<?> IncorrectEmailOrPassword(IncorrectEmailOrPasswordException e){
    return ResponseEntity.status(404).body(e.getMessage());
 }
}
