package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.repositories.VetRepository;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by jt on 8/5/18.
 * LEZIONE 152
 * Lo annoto come component in modo
 * che sia rilevato da Spring come stereotipo
 * lo annoto come profile springdatajpa
 * in modo che possa essere attivo
 * solo con il profilo attivo
 * Ovviamente nel caso in cui non sia
 * rilevato eventuali riferimenti andranno in errore
 * evita anche la possibilita che sia rilevato
 * due volte entro lo springCOntext
 * altrimenti Spring non capisce quale
 * deve collegare
 * Aggiungo l'oggetto final repository istanziato nel costruttore
 * implemento l'interfaccia CrudService speicifica
 * relativa al dominio della classe
 */
@Service
@Profile("springdatajpa")
public class VetSDJpaService implements VetService {

    // LEZIONE 152
    // Aggiungo il repository che sara' istanziato
    // nel costruttore e usato all'interno dei metodi
    // che implementano l'interfaccia VetService
    private final VetRepository vetRepository;

    public VetSDJpaService(VetRepository vetRepository) {

        this.vetRepository = vetRepository;
    }

    @Override
    public Set<Vet> findAll() {
        // LEZION 152
        // Creo un nuovo Hashset
        // e lo passo in input al metodo foreach
        // che itera sull'oggetto iterabile
        // ritornato della chiamata sul repository
        // per aggiungere al nuovo hashset il risultato
        // dell'iterazione
        Set<Vet> vets = new HashSet<>();
        vetRepository.findAll().forEach(vets::add);
        return vets;
    }

    @Override
    public Vet findById(Long aLong) {

        // Possiamo opzionalmente
        // ritornare qualcosa si SpringDataJpa
        // oppure null
        return vetRepository.findById(aLong).orElse(null);
    }

    @Override
    public Vet save(Vet object) {

        return vetRepository.save(object);
    }

    @Override
    public void delete(Vet object) {

        vetRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {

        vetRepository.deleteById(aLong);
    }
}
