package it.epicode.gestioneviaggi_s6_l5.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import it.epicode.gestioneviaggi_s6_l5.entities.Dipendente;
import it.epicode.gestioneviaggi_s6_l5.exceptions.BadRequestException;
import it.epicode.gestioneviaggi_s6_l5.exceptions.NotFoundException;
import it.epicode.gestioneviaggi_s6_l5.payloads.DipendenteDTO;
import it.epicode.gestioneviaggi_s6_l5.repositories.DipendenteRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class DipendenteService {
    private final DipendenteRepository dipendenteRepository;
    private final Cloudinary cloudinary;

    public DipendenteService(DipendenteRepository dipendenteRepository, Cloudinary cloudinary) {
        this.dipendenteRepository = dipendenteRepository;
        this.cloudinary = cloudinary;
    }

    public Dipendente save(DipendenteDTO body) {
        if (dipendenteRepository.existsByEmail(body.email())) throw new BadRequestException("Email già in uso!");
        if (dipendenteRepository.existsByUsername(body.username())) throw new BadRequestException("Username già in uso!");

        Dipendente nuovo = new Dipendente(body.username(), body.nome(), body.cognome(), body.email());
        return dipendenteRepository.save(nuovo);
    }

    public List<Dipendente> findAll() {
        return dipendenteRepository.findAll();
    }

    public Dipendente findById(UUID id) {
        return dipendenteRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public String uploadAvatar(UUID id, MultipartFile file) {
        Dipendente dipendente = this.findById(id);
        try {
            // Carica l'immagine su Cloudinary
            String url = (String) cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
            dipendente.setAvatarUrl(url);
            dipendenteRepository.save(dipendente);
            return url;
        } catch (IOException e) {
            throw new BadRequestException("Errore durante l'upload dell'immagine");
        }
    }
}
