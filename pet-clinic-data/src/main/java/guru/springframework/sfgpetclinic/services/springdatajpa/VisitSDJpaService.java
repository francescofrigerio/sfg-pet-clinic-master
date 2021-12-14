package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.repositories.VisitRepository;
import guru.springframework.sfgpetclinic.services.VisitService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by jt on 8/7/18.
 * LEZIONE 158
 * ADD SPRING DATA JPA VISIT SERVICE
 * CREO UN IMPLEMENTAZIONE
 * BASATA SULL'IMPLEMENTAZIONE DELLA MAPPA
 * NELL'IMPLEMENTAZIONE DI SPRING DATA JPA
 * E POI SI POTRA FAR FUNZIONARE IL TUTTO
 * TRAMITE I PROFILI ATTIVI
 * SI UTILIZZANO I VisitRepository VISTO IN PRECEDENZA
 *
 * STESSO COMMENTO DELLE ALTRE CLASSI
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
 * Qui non si estende da VisitMapService
 * perche si fa affidamento diretto su SpringDataJpa
 */
@Service
@Profile("springdatajpa")
public class VisitSDJpaService implements VisitService {

    private final VisitRepository visitRepository;

    public VisitSDJpaService(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    @Override
    public Set<Visit> findAll() {
        Set<Visit> visits = new HashSet<>();
        visitRepository.findAll().forEach(visits::add);
        return visits;
    }

    @Override
    public Visit findById(Long aLong) {

        return visitRepository.findById(aLong).orElse(null);
    }

    @Override
    public Visit save(Visit object) {

        return visitRepository.save(object);
    }

    @Override
    public void delete(Visit object) {

        visitRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {

        visitRepository.deleteById(aLong);
    }
}
