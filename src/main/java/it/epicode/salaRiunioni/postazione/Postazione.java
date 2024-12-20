package it.epicode.salaRiunioni.postazione;

import it.epicode.salaRiunioni.edificio.Edificio;
import it.epicode.salaRiunioni.prenotazione.Prenotazione;
import it.epicode.salaRiunioni.utente.Utente;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "postazioni")
public class Postazione {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String codice;

    private String descrizione;

    @Column(name = "tipo_sala",nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoPostazione tipoSala;

    @Column(name = "numero_massimo_partecipanti",nullable = false)
    private int numeroMassimoPartecipanti;


    @ManyToOne
    private Edificio edificio;

    @Override
    public String toString() {
        return "Postazione{" +
                "id=" + id +
                ", codice='" + codice + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", tipo=" + tipoSala +
                ", maxOccupanti=" + numeroMassimoPartecipanti +
                ", edificio=" + edificio.getNomeEdificio() + // Mostra solo il nome dell'edificio
                '}';
    }
}