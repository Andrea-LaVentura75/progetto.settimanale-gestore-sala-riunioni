package it.epicode.salaRiunioni.prenotazione;

import it.epicode.salaRiunioni.edificio.Edificio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione,Long> {

    @Query("SELECT COUNT(p) > 0 FROM Prenotazione p WHERE p.postazione.id = :postazioneId AND p.dataPrenotazione = :data")
    boolean existsByPostazioneAndData(@Param("postazioneId") Long postazioneId, @Param("data") LocalDate data);
}
