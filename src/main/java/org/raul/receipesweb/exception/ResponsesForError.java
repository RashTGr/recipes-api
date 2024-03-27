package org.raul.receipesweb.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ResponsesForError {
    private int status;
    private String message;
    private LocalDateTime timestamp;
}
