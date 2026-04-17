package it.epicode.gestioneviaggi_s6_l5.exceptions;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ErrorsPayload {
    private String message;
    private LocalDateTime timestamp;
}
