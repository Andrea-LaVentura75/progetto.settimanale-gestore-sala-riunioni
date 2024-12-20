package it.epicode.salaRiunioni.Configuration;

import com.github.javafaker.Faker;
import it.epicode.salaRiunioni.edificio.Edificio;
import it.epicode.salaRiunioni.edificio.EdificioRepository;
import it.epicode.salaRiunioni.postazione.Postazione;
import it.epicode.salaRiunioni.postazione.PostazioneRepository;
import it.epicode.salaRiunioni.postazione.TipoPostazione;
import it.epicode.salaRiunioni.utente.Utente;
import it.epicode.salaRiunioni.utente.UtenteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.stream.IntStream;

@Configuration
public class DatiConfiguration {
    @Bean
    CommandLineRunner initDatabase(UtenteRepository utenteRepository, EdificioRepository edificioRepository, PostazioneRepository postazioneRepository) {
        return args -> {
            if (utenteRepository.count() > 0 && edificioRepository.count() > 0 && postazioneRepository.count() > 0) {
                System.out.println("Il database è già popolato. Nessun dato aggiunto.");
                return;
            }

            Faker faker = new Faker();

            // Creazione Edifici
            IntStream.range(0, 5).forEach(i -> {
                Edificio edificio = new Edificio();
                edificio.setNomeEdificio(faker.company().name());
                edificio.setIndirizzo(faker.address().streetAddress());
                edificio.setCitta(faker.address().city());
                edificioRepository.save(edificio);

                // Creazione Postazioni
                IntStream.range(0, 3).forEach(j -> {
                    Postazione postazione = new Postazione();
                    postazione.setCodice(faker.code().isbn10());
                    postazione.setDescrizione(faker.lorem().sentence());
                    postazione.setTipoSala(TipoPostazione.values()[faker.number().numberBetween(0, TipoPostazione.values().length)]);
                    postazione.setNumeroMassimoPartecipanti(faker.number().numberBetween(1, 10));
                    postazione.setEdificio(edificio);
                    postazioneRepository.save(postazione);
                });
            });

            // Creazione Utenti
            IntStream.range(0, 10).forEach(i -> {
                Utente utente = new Utente();
                utente.setUsername(faker.name().username());
                utente.setNomeCompleto(faker.name().fullName());
                utente.setEmail(faker.internet().emailAddress());
                utenteRepository.save(utente);
            });
        };
    }
}
