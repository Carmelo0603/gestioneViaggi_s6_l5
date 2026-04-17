package it.epicode.gestioneviaggi_s6_l5.services;

import it.epicode.gestioneviaggi_s6_l5.entities.Dipendente;
import it.epicode.gestioneviaggi_s6_l5.entities.Prenotazione;
import it.epicode.gestioneviaggi_s6_l5.entities.Viaggio;
import it.epicode.gestioneviaggi_s6_l5.exceptions.BadRequestException;
import it.epicode.gestioneviaggi_s6_l5.payloads.PrenotazioneDTO;
import it.epicode.gestioneviaggi_s6_l5.repositories.PrenotazioneRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrenotazioneService {
    private final PrenotazioneRepository prenotazioneRepository;
    private final DipendenteService dipendenteService;
    private final ViaggioService viaggioService;

    public PrenotazioneService(PrenotazioneRepository prenotazioneRepository, DipendenteService dipendenteService, ViaggioService viaggioService) {
        this.prenotazioneRepository = prenotazioneRepository;
        this.dipendenteService = dipendenteService;
        this.viaggioService = viaggioService;
    }

    public Prenotazione save(PrenotazioneDTO body) {
        Dipendente dipendente = dipendenteService.findById(body.dipendenteId());
        Viaggio viaggio = viaggioService.findById(body.viaggioId());

        // Controllo della data, se esiste già una prenotazione per quel dipendente in quella data, lancia eccezione
        if (prenotazioneRepository.existsByDipendenteAndViaggioData(dipendente, viaggio.getData())) {
            throw new BadRequestException("Il dipendente è già impegnato in un viaggio in data " + viaggio.getData());
        }

        Prenotazione nuovaPrenotazione = new Prenotazione(viaggio, dipendente, body.notePreferenze());
        return prenotazioneRepository.save(nuovaPrenotazione);
    }

    public List<Prenotazione> findAll() {
        return prenotazioneRepository.findAll();
    }
}
