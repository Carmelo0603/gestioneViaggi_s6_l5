package it.epicode.gestioneviaggi_s6_l5.controllers;

import it.epicode.gestioneviaggi_s6_l5.entities.Dipendente;
import it.epicode.gestioneviaggi_s6_l5.payloads.DipendenteDTO;
import it.epicode.gestioneviaggi_s6_l5.services.DipendenteService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/dipendenti")
public class DipendenteController {
    private final DipendenteService dipendenteService;

    public DipendenteController(DipendenteService dipendenteService) {
        this.dipendenteService = dipendenteService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Dipendente save(@RequestBody @Validated DipendenteDTO body) {
        return dipendenteService.save(body);
    }

    @GetMapping
    public List<Dipendente> getAll() {
        return dipendenteService.findAll();
    }

    // Rotta PATCH per aggiornare solo l'immagine, deve ricevere un form-data su Postman
    @PatchMapping("/{id}/avatar")
    public String uploadAvatar(@PathVariable UUID id, @RequestParam("avatar") MultipartFile file) {
        return dipendenteService.uploadAvatar(id, file);
    }
}
