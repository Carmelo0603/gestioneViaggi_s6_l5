package it.epicode.gestioneviaggi_s6_l5.entities;

import it.epicode.gestioneviaggi_s6_l5.enums.StatoViaggio;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "viaggi")
@Getter
@Setter
@NoArgsConstructor
public class Viaggio {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;

    @Column(nullable = false)
    private String destinazione;

    @Column(nullable = false)
    private LocalDate data;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatoViaggio stato;

    // Quando creiamo un viaggio, lo stato di default è IN_PROGRAMMA
    public Viaggio(String destinazione, LocalDate data) {
        this.destinazione = destinazione;
        this.data = data;
        this.stato = StatoViaggio.IN_PROGRAMMA;
    }
}
