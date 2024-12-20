package it.epicode.salaRiunioni.utente;

import it.epicode.salaRiunioni.prenotazione.Prenotazione;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "utenti")
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(name = "nome_completo",nullable = false)
    private String nomeCompleto;


    @Column(nullable = false)
    private String email;


}