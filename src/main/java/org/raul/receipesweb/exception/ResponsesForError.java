package org.raul.receipesweb.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class ResponsesForError {
    private int status;
    private String message;
    private LocalDateTime timestamp;
}
