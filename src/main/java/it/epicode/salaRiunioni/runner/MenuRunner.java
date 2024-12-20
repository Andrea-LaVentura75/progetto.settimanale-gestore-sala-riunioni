package it.epicode.salaRiunioni.runner;

import it.epicode.salaRiunioni.postazione.Postazione;
import it.epicode.salaRiunioni.postazione.PostazioneRepository;
import it.epicode.salaRiunioni.postazione.TipoPostazione;
import it.epicode.salaRiunioni.prenotazione.Prenotazione;
import it.epicode.salaRiunioni.prenotazione.PrenotazioneRepository;
import it.epicode.salaRiunioni.utente.Utente;
import it.epicode.salaRiunioni.utente.UtenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class MenuRunner {
    private final UtenteRepository utenteRepository;
    private final PostazioneRepository postazioneRepository;
    private final PrenotazioneRepository prenotazioneRepository;

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Menu Prenotazioni ---");
            System.out.println("1. Ricerca Postazioni per Tipo e Città");
            System.out.println("2. Prenota una Postazione");
            System.out.println("3. Esci");

            int scelta = scanner.nextInt();
            scanner.nextLine();

            switch (scelta) {
                case 1:
                    System.out.print("Inserisci il Tipo di Postazione (PRIVATO, OPENSPACE, SALA_RIUNIONI): ");
                    TipoPostazione tipoPostazione;
                    try {
                        tipoPostazione = TipoPostazione.valueOf(scanner.nextLine().toUpperCase());
                    } catch (IllegalArgumentException e) {
                        System.out.println("Tipo di postazione non valido. Riprova.");
                        break;
                    }

                    System.out.print("Inserisci la Città: ");
                    String citta = scanner.nextLine();

                    List<Postazione> postazioni = postazioneRepository.findByTipoAndCitta(tipoPostazione, citta);
                    if (postazioni.isEmpty()) {
                        System.out.println("Nessuna postazione trovata per il tipo e la città specificati.");
                    } else {
                        postazioni.forEach(System.out::println);
                    }
                    break;
                case 2:
                    System.out.print("Inserisci ID Utente: ");
                    Long userId = scanner.nextLong();
                    System.out.print("Inserisci ID Postazione: ");
                    Long postazioneId = scanner.nextLong();
                    System.out.print("Inserisci Data (YYYY-MM-DD): ");
                    String dataStr = scanner.next();

                    LocalDate data = LocalDate.parse(dataStr);
                    Utente utente = utenteRepository.findById(userId).orElse(null);
                    Postazione postazione = postazioneRepository.findById(postazioneId).orElse(null);

                    if (utente != null && postazione != null) {
                        boolean isAvailable = !prenotazioneRepository.existsByPostazioneAndData(postazione.getId(), data);

                        if (isAvailable) {
                            Prenotazione prenotazione = new Prenotazione();
                            prenotazione.setUtente(utente);
                            prenotazione.setPostazione(postazione);
                            prenotazione.setDataPrenotazione(data);
                            prenotazione.setValiditaPrenotazione(LocalDateTime.now().plusHours(24));
                            prenotazioneRepository.save(prenotazione);
                            System.out.println("Prenotazione effettuata con successo! Valida fino a: " + prenotazione.getValiditaPrenotazione());
                        } else {
                            System.out.println("Postazione non disponibile per la data selezionata. Un altro utente ha già prenotato questa postazione in questa data.");
                        }
                    } else {
                        System.out.println("Utente o Postazione non trovati.");
                    }
                    break;
                case 3:
                    System.out.println("Uscita in corso...");
                    return;
                default:
                    System.out.println("Scelta non valida.");
            }
        }
    }
}
