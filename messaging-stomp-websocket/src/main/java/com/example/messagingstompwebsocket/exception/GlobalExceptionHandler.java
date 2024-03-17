package com.example.messagingstompwebsocket.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.naming.AuthenticationException;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(HttpClientErrorException.Forbidden.class)
    public ResponseEntity<ExceptionResponse> httpClientError(HttpClientErrorException.Forbidden ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setStatusCode(HttpStatus.FORBIDDEN.toString().substring(0, 3));
        response.setError(HttpStatus.FORBIDDEN.getReasonPhrase());
        response.setMessage(ex.getMessage());
        response.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(SuchElementExistsException.class)
    public ResponseEntity<ExceptionResponse> suchObjectExists(SuchElementExistsException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setStatusCode(HttpStatus.BAD_REQUEST.toString().substring(0,3));
        response.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
        response.setMessage(ex.getMessage());
        response.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ExceptionResponse> notExists(NoSuchElementException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setStatusCode(HttpStatus.BAD_REQUEST.toString().substring(0,3));
        response.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
        response.setMessage(ex.getMessage());
        response.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(JwtExpiredException.class)
    public ResponseEntity<ExceptionResponse> jwtExpired(JwtExpiredException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED.toString().substring(0,3));
        response.setError(HttpStatus.UNAUTHORIZED.getReasonPhrase());
        response.setMessage(ex.getMessage());
        response.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.UNAUTHORIZED);
    }

//    @ExceptionHandler(BadCredentialsException.class)
//    public ResponseEntity<ExceptionResponse> noSuchElement(BadCredentialsException ex) {
//        ExceptionResponse response = new ExceptionResponse();
//        response.setStatusCode(HttpStatus.UNAUTHORIZED.toString().substring(0,3));
//        response.setError(HttpStatus.UNAUTHORIZED.getReasonPhrase());
//        response.setMessage(ex.getMessage());
//        response.setTimestamp(LocalDateTime.now());
//        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.UNAUTHORIZED);
//    }
}