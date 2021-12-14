package guru.springframework.sfgpetclinic.repositories;

import guru.springframework.sfgpetclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by jt on 8/5/18.
 * LEZIONE 150
 * Estende Interfaccia CrudRepository
 * specificando il Tipo generico Di Oggetto T come Owner e
 * il tipo generico di ID value dell' entita come Long
 * questo perche' il tipo del campo ID dell'oggetto
 * BaseEntity da cui ereditano Owner e Person è un Long
 * I due tipi di dati devono essere coerenti tra di loro.
 * ha delle alternative avanzate
 * come org.springframework.data.repository.PagingAndSortingRepository;
 * come org.springframework.data.jpa.repository.JpaRepository;
 * SpringDataJpa ci fornirà istanze di questi oggetti
 * in fase di runtime dell'applicazione in automatico
 * quindi noi li avremo disponibili per il nostro uso
 * a livello di codice dell'applicazione SpringBoot
 * all'interno del contesto si Spring
 * Non è detto che saranno utilizzati tutti
 * ma è bene che per ogni entita del modello di dati
 * ci sia la relativa interfaccia repository
 * L'alternativa alla tecnica che utilizza le interfacc
 * e implementare le classi Controller come si vede nel modulo web
 * in modo più orientato ai microservizi.
 * Questa soluzione invece può essere preferibile
 * nel senso che vogliamo sapere dove si trovano
 * i reposiory e dov'è il modello di dati relazionali
 * che si appoggerà sul db sottostante
 * Quindi questo è un approccio un pò più funzionale
 * e orientato al dominio degli oggetti con cui stiamo
 * lavorando. E' la stessa cosa ma con un organizzazione
 * diversa rispetto a quanto viene fatto con le classi
 * controller che si vede nel modulo web.
 * Nel progetto originario lo hanno organizzato
 * con le classi Controller non è giusto e dbagliato l'altro
 * solo una diversa scelta implememntativa
 * basata su un discorso funzionale basato sulle classi Repository
 */
public interface OwnerRepository extends CrudRepository<Owner, Long> {

    Owner findByLastName(String lastName);

    List<Owner> findAllByLastNameLike(String lastName);
}
