package br.com.project_bookstore_api.bookstore_api.exception;

import br.com.project_bookstore_api.bookstore_api.dto.ResponseExceptionDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.Arrays;

@ControllerAdvice
@Slf4j
public class GlobalHandlerException {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ResponseExceptionDto> handleEntityNotFound(EntityNotFoundException exception){
        log.error(Arrays.toString(exception.getStackTrace()));
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ResponseExceptionDto(
                        LocalDateTime.now(),
                        HttpStatus.NOT_FOUND.value(),
                        "not found test",
                        exception.getMessage()
                ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseExceptionDto> handleGenericException(Exception exception){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ResponseExceptionDto(
                        LocalDateTime.now(),
                        HttpStatus.NOT_FOUND.value(),
                        "internal server error test",
                        exception.getMessage()
                ));
    }
}