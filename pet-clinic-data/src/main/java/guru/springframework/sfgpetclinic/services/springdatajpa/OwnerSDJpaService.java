package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repositories.OwnerRepository;
import guru.springframework.sfgpetclinic.repositories.PetRepository;
import guru.springframework.sfgpetclinic.repositories.PetTypeRepository;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by jt on 8/5/18.
 * LEZIONE 151
 * OwnerSDJpaService OwnerSpringDataJpaService
 * AGGIUNGO OGGETTI FINAL REPOSITORY E CHIAMO
 * I METODI ATTRAVERSO L'INTERFACCIA
 * GLI OGGETTI SONO COSTRUITO CONTESTUALMENTE
 * AL COSTRUTTORE DELLA CLASSE
 * IMPLEMENTA L'INTERFACCIA OwnerService
 * DEFINITA NEL PACKAGE services
 * E ALL'INTERNO DEI METODI SONO USATI I
 * METODI DEI REPOSITORY PER IMPLEMENTARE LA FUNZIONALITA
 * ESPRESSA DA OwnerServices A LIVELLO astratto
 * VIENE CABLATO COME SERVIZIO TRAMITE @Services
 * IN MODO CHE SIA INNESTATO NEL CONTEXT DI SPRING ALL'AVVIO
 * DEFINISCO IL PROFILO COME ANNOTAZIONE E POI SUL FILE
 * APPLICATION.PROPERTIES IN QUESTO MODO
 * QUELLO CHE VIENE DEFINITO IN AUTOMATICO
 * NEL CONTESTO VIENE IGNORATO
 */
@Service
@Profile("springdatajpa")
// LEZIONE 151 SE COMMENTO QUI E IN OwnerMapService
// ho un errore runtime
// senza springdatajpa viene ignorato
// Si puo lavorare solo a livello di Mappatura del servizio
// o solo a livello di repository a far rilevare al contesto
// uno e/o l'altro.
// LEZIONE 151
public class OwnerSDJpaService implements OwnerService {

    private final OwnerRepository ownerRepository;
    // LEZIONE 151 non uso PetRepository PetTypeRepository
    // per il momento Se Aggiungo annotazione profile
    // possono essere esclusi visto che non
    // viene preso in considerazione il context di Spring

    private final PetRepository petRepository;
    private final PetTypeRepository petTypeRepository;

    public OwnerSDJpaService(OwnerRepository ownerRepository,
                             PetRepository petRepository,
                             PetTypeRepository petTypeRepository) {
        this.ownerRepository = ownerRepository;
        this.petRepository = petRepository;
        this.petTypeRepository = petTypeRepository;
    }

    @Override
    public Owner findByLastName(String lastName) {

        // RESTITUISCE UN PROPRIETARIO
        // TRAMITE LE QUERY DINAMICHE FORNITE
        // DA SPRINGDATAJPA ATTRAVERSO INTERFACCIA REPOSITORY
        // E PASSARE IL PARAMETRO IN INGRESSO
        return ownerRepository.findByLastName(lastName);
    }

    @Override
    public List<Owner> findAllByLastNameLike(String lastName) {
        return ownerRepository.findAllByLastNameLike(lastName);
    }

    @Override
    public Set<Owner> findAll() {
        Set<Owner> owners = new HashSet<>();
        // LEZIONE 151
        // Si potrebbe aggiungere un controllo sulla count
        // dell'oggetto iterabile fornito in ouput
        // dall'interfaccia repository
        ownerRepository.findAll().forEach(owners::add);
        return owners;
    }

    @Override
    public Owner findById(Long aLong) {

        // LEZIONE 151
        /*  STEP1 SCRIVO QUESTO CODICE
            POI INTELLJ NE PROPONE UNO
            PIU'COMPATTO SEMPRE
            CON LA STESSA LOGICA
            DI RESTITUIRE L'OPZIONB OPPURE NULL
         if (optionalOwner.isPresent())
        {
            return optionalOwner.get();
        } else {
            return null;
        }
        */

        /* STEP2
        Optional<Owner> optionalOwner = ownerRepository.findById(id);

        return optionalOwner.orElse(null);
        */
        // STEP3 MIGLIORO ANCORA IL CODICE
        // RESTITUISCE IL PROPRIETARIO SE LO TROVA
        // ALTRIMENTI NULL
        return ownerRepository.findById(aLong).orElse(null);
    }

    @Override
    public Owner save(Owner object) {
        System.out.println("START SAVE ON SPRING DATA JPA SERVICE");
        return ownerRepository.save(object);
    }

    @Override
    public void delete(Owner object) {

        ownerRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {

        ownerRepository.deleteById(aLong);
    }
}
