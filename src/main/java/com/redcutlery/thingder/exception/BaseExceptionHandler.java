package com.redcutlery.thingder.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Log4j2
@ControllerAdvice
public class BaseExceptionHandler {
    @ExceptionHandler(value = BaseException.ServerError.class)
    public ResponseEntity<Map<String, String>> ServerError(BaseException.ServerError e, WebRequest request) {
        Map<String, String> body = new HashMap<>();
        body.put("message", e.message);
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("details", request.getDescription(true));
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = BaseException.BadRequest.class)
    public ResponseEntity<Map<String, String>> BadRequest(BaseException.BadRequest e, WebRequest request) {
        Map<String, String> body = new HashMap<>();
        body.put("message", e.message);
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("details", request.getDescription(true));
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = BaseException.Unauthorized.class)
    public ResponseEntity<Map<String, String>> Unauthorized(BaseException.Unauthorized e, WebRequest request) {
        Map<String, String> body = new HashMap<>();
        body.put("message", e.message);
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("details", request.getDescription(true));
        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = BaseException.Forbidden.class)
    public ResponseEntity<Map<String, String>> Forbidden(BaseException.Forbidden e, WebRequest request) {
        Map<String, String> body = new HashMap<>();
        body.put("message", e.message);
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("details", request.getDescription(true));
        return new ResponseEntity<>(body, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = BaseException.NotFound.class)
    public ResponseEntity<Map<String, String>> NotFound(BaseException.NotFound e, WebRequest request) {
        Map<String, String> body = new HashMap<>();
        body.put("message", e.message);
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("details", request.getDescription(true));
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

}
