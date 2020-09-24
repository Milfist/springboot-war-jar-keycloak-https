package es.caser.desktopmenuservice.config;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.FileNotFoundException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Log4j2
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public final class MenuExceptionHandler {

    @ExceptionHandler(FileNotFoundException.class)
    protected ResponseEntity<String> handleFileNotFound(FileNotFoundException ex) {
        return buildResponseEntity(NOT_FOUND, ex.getMessage());
    }

    private ResponseEntity<String> buildResponseEntity(HttpStatus httpStatus, String message) {
        log.error("EXCEPTION: " + message);
        return new ResponseEntity<>(httpStatus);
    }
}
