package it.epicode.salaRiunioni.postazione;

import it.epicode.salaRiunioni.edificio.Edificio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;



public interface PostazioneRepository extends JpaRepository<Postazione, Long> {

    @Query("SELECT p FROM Postazione p JOIN p.edificio e WHERE p.tipoSala = :tipo AND e.citta = :citta")
    List<Postazione> findByTipoAndCitta(@Param("tipo") TipoPostazione tipo, @Param("citta") String citta);

}
