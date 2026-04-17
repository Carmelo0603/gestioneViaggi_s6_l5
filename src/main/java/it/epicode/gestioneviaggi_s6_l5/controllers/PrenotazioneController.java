package it.epicode.gestioneviaggi_s6_l5.controllers;

import it.epicode.gestioneviaggi_s6_l5.entities.Prenotazione;
import it.epicode.gestioneviaggi_s6_l5.payloads.PrenotazioneDTO;
import it.epicode.gestioneviaggi_s6_l5.services.PrenotazioneService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {
    private final PrenotazioneService prenotazioneService;

    public PrenotazioneController(PrenotazioneService prenotazioneService) {
        this.prenotazioneService = prenotazioneService;
    }

    // Endpoint per assegnare un dipendente a un viaggio
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Prenotazione save(@RequestBody @Validated PrenotazioneDTO body) {
        return prenotazioneService.save(body);
    }

    @GetMapping
    public List<Prenotazione> getAll() {
        return prenotazioneService.findAll();
    }
}
