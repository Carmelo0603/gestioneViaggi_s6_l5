package it.epicode.gestioneviaggi_s6_l5.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;
import org.springframework.web.bind.MethodArgumentNotValidException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ErrorsHandler {

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsPayload handleBadRequest(BadRequestException ex) {
        return new ErrorsPayload(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorsPayload handleNotFound(NotFoundException ex) {
        return new ErrorsPayload(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorsPayload handleGeneric(Exception ex) {
        ex.printStackTrace();
        return new ErrorsPayload("Errore generico del server", LocalDateTime.now());
    }



    // Nuovo gestore per gli errori dei DTO (@NotBlank, @Email, ecc.)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsPayload handleValidationErrors(MethodArgumentNotValidException ex) {
        // Prende tutti gli errori di validazione e li unisce in una singola stringa separata da punti
        String message = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.joining(". "));

        return new ErrorsPayload(message, LocalDateTime.now());
    }
}
