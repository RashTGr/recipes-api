package org.raul.receipesweb.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Collections;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponsesForError> resourceNotFoundException(ResourceNotFoundException e) {
        ResponsesForError responsesForError = new ResponsesForError(
                HttpStatus.NOT_FOUND.value(),
                e.getMessage(),
                LocalDateTime.now());

        return new ResponseEntity<>(responsesForError, HttpStatus.NOT_FOUND);
    }
}
