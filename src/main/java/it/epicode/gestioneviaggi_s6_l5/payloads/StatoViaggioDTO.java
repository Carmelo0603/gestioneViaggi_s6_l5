package it.epicode.gestioneviaggi_s6_l5.payloads;

import it.epicode.gestioneviaggi_s6_l5.enums.StatoViaggio;
import jakarta.validation.constraints.NotNull;

public record StatoViaggioDTO(
        @NotNull(message = "Lo stato è obbligatorio") StatoViaggio stato
) {}
