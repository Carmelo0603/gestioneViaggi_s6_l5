package it.epicode.gestioneviaggi_s6_l5.payloads;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record PrenotazioneDTO(
        @NotNull(message = "ID del viaggio obbligatorio") UUID viaggioId,
        @NotNull(message = "ID del dipendente obbligatorio") UUID dipendenteId,
        String notePreferenze
) {}