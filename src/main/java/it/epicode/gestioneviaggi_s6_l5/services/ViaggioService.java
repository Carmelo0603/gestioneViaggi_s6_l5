package it.epicode.gestioneviaggi_s6_l5.services;

import it.epicode.gestioneviaggi_s6_l5.entities.Viaggio;
import it.epicode.gestioneviaggi_s6_l5.enums.StatoViaggio;
import it.epicode.gestioneviaggi_s6_l5.exceptions.NotFoundException;
import it.epicode.gestioneviaggi_s6_l5.payloads.ViaggioDTO;
import it.epicode.gestioneviaggi_s6_l5.repositories.ViaggioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ViaggioService {
    private final ViaggioRepository viaggioRepository;

    public ViaggioService(ViaggioRepository viaggioRepository) {
        this.viaggioRepository = viaggioRepository;
    }

    public Viaggio save(ViaggioDTO body) {
        Viaggio nuovo = new Viaggio(body.destinazione(), body.data());
        return viaggioRepository.save(nuovo);
    }

    public List<Viaggio> findAll() {
        return viaggioRepository.findAll();
    }

    public Viaggio findById(UUID id) {
        return viaggioRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Viaggio cambiaStato(UUID id, StatoViaggio nuovoStato) {
        Viaggio viaggio = this.findById(id);
        viaggio.setStato(nuovoStato);
        return viaggioRepository.save(viaggio);
    }
}