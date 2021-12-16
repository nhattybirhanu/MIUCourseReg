package miu.edu.com.courseregistrationsystem.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.swing.*;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Date;
@ControllerAdvice
@RestController
public class MyExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleUserNotfoundException(Exception ex, WebRequest request){
        logger.info(request);
        ExceptionResponse exceptionResponse=new  ExceptionResponse(new Date(),ex.getMessage(),request.getDescription(false));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }
    @ExceptionHandler(RoleException.class)
    public ResponseEntity<?> handleRoleException(Exception ex, WebRequest request){
        logger.info(request);
        ExceptionResponse exceptionResponse=new  ExceptionResponse(new Date(),ex.getMessage(),request.getDescription(false));
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exceptionResponse);
    }
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExceptionResponse exceptionResponse=new  ExceptionResponse(new Date(),ex.getMessage(),ex.getBindingResult().toString());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }
    @ExceptionHandler({SQLIntegrityConstraintViolationException.class,UniqueExceptionHandler.class})
    public ResponseEntity<?> handleDuplicateEntryException(Exception ex, WebRequest request){
        logger.info(request);
        ExceptionResponse exceptionResponse=new  ExceptionResponse(new Date(),ex.getMessage(),request.getDescription(false));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }
}
