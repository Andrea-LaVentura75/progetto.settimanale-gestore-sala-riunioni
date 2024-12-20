package it.epicode.salaRiunioni.prenotazione;

import it.epicode.salaRiunioni.postazione.Postazione;
import it.epicode.salaRiunioni.utente.Utente;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "prenotazioni")
public class Prenotazione {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "data_prenotazione", nullable = false)
    private LocalDate dataPrenotazione;

    private LocalDateTime validitaPrenotazione;


    @ManyToOne
    private Utente utente;

    @ManyToOne
    private Postazione postazione;


}