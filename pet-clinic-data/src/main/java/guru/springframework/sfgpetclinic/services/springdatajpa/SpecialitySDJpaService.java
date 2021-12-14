package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;
import guru.springframework.sfgpetclinic.services.SpecialtyService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by jt on 8/5/18.
 * LEZIONE 155
 *  Lo annoto come component in modo
 *  che sia rilevato da Spring come stereotipo
 *  lo annoto come profile springdatajpa
 *  in modo che possa essere attivo
 *  solo con il profilo attivo
 *  Ovviamente nel caso in cui non sia
 *  rilevato eventuali riferimenti andranno in errore
 *  evita anche la possibilita che sia rilevato
 *  due volte entro lo springCOntext
 *  altrimenti Spring non capisce quale
 *  deve collegare
 *  Aggiungo l'oggetto final repository istanziato nel costruttore
 *  implemento l'interfaccia CrudService speicifica
 *  relativa al dominio della classe
 *  Qui non si estende da MapService
 *  perche si fa affidamento diretto su SpringDataJpa
 *  Servizio Di Mappe e servizi di animali domestici
 *  Implementiamo tutti i metodi dell'interfaccia SpecialtyService
 *  che non è altro che un estensione dell'interfaccua CrudService
 */
@Service
@Profile("springdatajpa")
public class SpecialitySDJpaService implements SpecialtyService {

    // LEZIONE 155
    // Proprieta final e' un codice difensivo
    // che impedisce le modifiche all'oggetto
    // una volta che viene creato nel costruttore
    // Utilizziamo un oggetto repository
    // creato nel costruttore per realizzare
    // tutte le operazioni di persistenza verso il DB
    private final SpecialtyRepository specialtyRepository;

    public SpecialitySDJpaService(SpecialtyRepository specialtyRepository) {
        this.specialtyRepository = specialtyRepository;
    }

    @Override
    public Set<Speciality> findAll() {
        Set<Speciality> specialities = new HashSet<>();
        specialtyRepository.findAll().forEach(specialities::add);
        return specialities;
    }

    @Override
    public Speciality findById(Long aLong) {

        // Ritorna un optional vedere CrudRepository
        return specialtyRepository.findById(aLong).orElse(null);
    }

    @Override
    public Speciality save(Speciality object) {

        return specialtyRepository.save(object);
    }

    @Override
    public void delete(Speciality object) {

        specialtyRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {

        specialtyRepository.deleteById(aLong);
    }
}
