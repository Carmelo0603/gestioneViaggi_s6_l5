package it.epicode.gestioneviaggi_s6_l5.repositories;

import it.epicode.gestioneviaggi_s6_l5.entities.Viaggio;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ViaggioRepository extends JpaRepository<Viaggio, UUID> {}
