package it.epicode.gestioneviaggi_s6_l5.repositories;

import it.epicode.gestioneviaggi_s6_l5.entities.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface DipendenteRepository extends JpaRepository<Dipendente, UUID> {
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}
