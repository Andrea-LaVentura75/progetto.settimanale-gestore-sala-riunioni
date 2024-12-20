package it.epicode.salaRiunioni.utente;

import it.epicode.salaRiunioni.edificio.Edificio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtenteRepository extends JpaRepository<Utente,Long> {
}
