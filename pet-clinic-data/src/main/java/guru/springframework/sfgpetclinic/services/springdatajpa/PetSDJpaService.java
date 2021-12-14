package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.repositories.PetRepository;
import guru.springframework.sfgpetclinic.services.PetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by jt on 8/5/18.
 * LEZIONE 154
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
 * Qui non si estende da PetMapService
 * perche si fa affidamento diretto su SpringDataJpa
 */
@Service
@Profile("springdatajpa")
public class PetSDJpaService implements PetService {

    private final PetRepository petRepository;

    public PetSDJpaService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public Set<Pet> findAll() {
        Set<Pet> pets = new HashSet<>();
        // Aggiungo al ciclo foreach
        // la struttura hashset e il metodo
        // a cui fare riferimento riguarda alla struttura
        // stessa quindi aggiunfo quello che trovo
        // in risposta dal repository alla struttura hasset
        petRepository.findAll().forEach(pets::add);
        return pets;
    }

    @Override
    public Pet findById(Long aLong) {
        // se non è presente ritorno un valore null
        return petRepository.findById(aLong).orElse(null);
    }

    @Override
    public Pet save(Pet object) {

        return petRepository.save(object);
    }

    @Override
    public void delete(Pet object) {

        petRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {

        petRepository.deleteById(aLong);
    }
}
