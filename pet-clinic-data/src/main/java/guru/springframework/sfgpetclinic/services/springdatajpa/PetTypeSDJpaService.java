package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.repositories.PetTypeRepository;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by jt on 8/5/18.
 * LEZIONE 153
 * Lo annoto come component in modo
 * he sia rilevato da Spring come stereotipo
 * lo annoto come profile springdatajpa
 * in modo che possa essere attivo
 * solo con il profilo attivo
 * Ovviamente nel caso in cui non sia
 * rilevato eventuali riferimenti andranno in errore
 * evita anche la possibilita che sia rilevato
 * due volte entro lo springCOntext
 * altrimenti Spring non capisce quale
 *  * deve collegare
 * Aggiungo l'oggetto final repository istanziato nel costruttore
 * implemento l'interfaccia CrudService speicifica
 * relativa al dominio della classe
 */
@Service
@Profile("springdatajpa")
public class PetTypeSDJpaService implements PetTypeService {

    private final PetTypeRepository petTypeRepository;

    public PetTypeSDJpaService(PetTypeRepository petTypeRepository) {
        this.petTypeRepository = petTypeRepository;
    }

    @Override
    public Set<PetType> findAll() {
        Set<PetType> petTypes = new HashSet<>();
        petTypeRepository.findAll().forEach(petTypes::add);
        return petTypes;
    }

    @Override
    public PetType findById(Long aLong) {

        // LEZIONE 153
        // Il valore di ritorno della ricerca x ID
        // è un oggetto Optional pertanto è possibile
        // come si è visto per l'oggetto OwnerSdSpaService
        // restituire l'oggetto oppure Null quando non si trova nulla
        return petTypeRepository.findById(aLong).orElse(null);
    }

    @Override
    public PetType save(PetType object) {

        return petTypeRepository.save(object);
    }

    @Override
    public void delete(PetType object) {

        petTypeRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {

        petTypeRepository.deleteById(aLong);
    }
}
