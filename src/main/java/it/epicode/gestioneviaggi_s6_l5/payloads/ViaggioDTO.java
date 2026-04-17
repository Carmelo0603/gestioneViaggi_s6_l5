package it.epicode.gestioneviaggi_s6_l5.payloads;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record ViaggioDTO(
        @NotBlank(message = "Destinazione obbligatoria") String destinazione,
        @NotNull(message = "Data obbligatoria") @FutureOrPresent(message = "La data deve essere futura o odierna") LocalDate data
) {}
