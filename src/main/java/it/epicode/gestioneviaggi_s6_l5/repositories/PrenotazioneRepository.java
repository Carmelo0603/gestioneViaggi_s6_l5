package it.epicode.gestioneviaggi_s6_l5.repositories;

import it.epicode.gestioneviaggi_s6_l5.entities.Dipendente;
import it.epicode.gestioneviaggi_s6_l5.entities.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.UUID;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, UUID> {
    // Questo metodo è cruciale per bloccare i dipendenti che vogliono fare due viaggi lo stesso giorno
    boolean existsByDipendenteAndViaggioData(Dipendente dipendente, LocalDate data);
}
