//package com.modul2.learning.exceptions;
//
//import jakarta.persistence.EntityNotFoundException;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//import static org.springframework.http.HttpStatus.NOT_FOUND;
//
//@ControllerAdvice
//public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
//
//    @ExceptionHandler(EntityNotFoundException.class)
//    public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException entityNotFoundException) {
//        ErrorDetail error = new ErrorDetail(entityNotFoundException.getMessage());
////        return ResponseEntity.notFound().build(error);
//        return new ResponseEntity<>(error, NOT_FOUND);
//    }
//
//}
