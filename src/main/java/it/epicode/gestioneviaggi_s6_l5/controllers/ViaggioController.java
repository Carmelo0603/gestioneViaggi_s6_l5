package it.epicode.gestioneviaggi_s6_l5.controllers;

import it.epicode.gestioneviaggi_s6_l5.entities.Viaggio;
import it.epicode.gestioneviaggi_s6_l5.payloads.StatoViaggioDTO;
import it.epicode.gestioneviaggi_s6_l5.payloads.ViaggioDTO;
import it.epicode.gestioneviaggi_s6_l5.services.ViaggioService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/viaggi")
public class ViaggioController {
    private final ViaggioService viaggioService;

    public ViaggioController(ViaggioService viaggioService) {
        this.viaggioService = viaggioService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Viaggio save(@RequestBody @Validated ViaggioDTO body) {
        return viaggioService.save(body);
    }

    @GetMapping
    public List<Viaggio> getAll() {
        return viaggioService.findAll();
    }

    // Rotta PATCH per modificare solo lo stato (da IN_PROGRAMMA a COMPLETATO)
    @PatchMapping("/{id}/stato")
    public Viaggio cambiaStato(@PathVariable UUID id, @RequestBody @Validated StatoViaggioDTO body) {
        return viaggioService.cambiaStato(id, body.stato());
    }
}
