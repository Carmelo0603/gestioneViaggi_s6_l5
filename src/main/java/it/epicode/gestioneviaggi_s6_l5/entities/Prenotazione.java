package it.epicode.gestioneviaggi_s6_l5.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "prenotazioni")
@Getter
@Setter
@NoArgsConstructor
public class Prenotazione {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;

    // Relazione unidirezionale: la prenotazione ha il viaggio, ma il viaggio non ha le prenotazioni
    @ManyToOne
    @JoinColumn(name = "viaggio_id", nullable = false)
    private Viaggio viaggio;

    // Relazione unidirezionale con il dipendente
    @ManyToOne
    @JoinColumn(name = "dipendente_id", nullable = false)
    private Dipendente dipendente;

    @Column(name = "data_richiesta", nullable = false)
    private LocalDate dataRichiesta;

    @Column(name = "note_preferenze")
    private String notePreferenze;

    public Prenotazione(Viaggio viaggio, Dipendente dipendente, String notePreferenze) {
        this.viaggio = viaggio;
        this.dipendente = dipendente;
        this.dataRichiesta = LocalDate.now();
        this.notePreferenze = notePreferenze;
    }
}
