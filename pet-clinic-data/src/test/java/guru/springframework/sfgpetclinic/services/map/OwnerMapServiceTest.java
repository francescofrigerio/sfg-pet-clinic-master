package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

// LEZIONE 185
// Testiamo l'implementazione della HashMap dei
// proprietari che al suo interno usa i servizi
// Dedicati ad animali e tipi di animali
// Vediamo come impostare il codice di Junit5
// Inizio con tasto + generate + test sul nome della classe
// Testo tutte le operazioni Crud del servizio

class OwnerMapServiceTest {

    // LEZIONE 185
    // Normalmente si userebbe Mockito
    // Ma per adesso facciamo a meno
    // e usiamo direttamente un servizio HashMap semplice
    // Per cui posso implementare i test
    // relativi a tutti i metodi della classe
    OwnerMapService ownerMapService;

    final Long ownerId = 1L;
    final String lastName = "Smith";

    // LEZIONE 185
    // TEST ESEGUITO PRIMA DI OGNI METODO
    @BeforeEach
    void setUp() {
        // LEZIONE 185 PASSO IN INPUT
        // AL COSTRUTTORE L'ISTANZA DEI SERVIZI
        // ANIMALI E TIPI DI ANIMALI
        // NELLA DICHIARAZIONE DEL COSTRUTTORE
        // VIENE SPECIFICATA l'INTERFACCIA DEI DUE
        // SERVIZI MA QUI VIENE PASSATA L'IMPLEMENTAZIONE
        // DELL'INTERFACCIA IMITIAMO IN QUESTO MODO
        // QUELLO CHE FA SPRING BOOT PER INIETTARE
        // LA CLASSE NEL CONTESTO DI SPRING GRAZIE ALLA CONFIGURAZIONE
        // FACCIAMO ALLO STESSO L'INIEZIONE DELLA DIPENDENZA
        ownerMapService = new OwnerMapService( new PetTypeMapService(),
                                               new PetMapService());

        System.out.println("Start save Owner " + ownerId + " " + lastName);
        // USO IL SERVIZIO DI HASHMAP PER SALVARE
        // IL PROPRIETARIO CON ID E COGNOME SPECIFICATII
        // QUINDI LO STRETTO INDISPENSABILE
        // INIZIALIZZANDO IL MIO OwnerMapService E INSERENDO
        // SUL DATABASE IL PROPRIETARIO DOPO AVERLO COSTRUITO CON LA BUILD
        ownerMapService.save(Owner.builder().id(ownerId).lastName(lastName).build());

        System.out.println("End save Owner " + ownerId + " " + lastName);
    }

    @Test
    void findAll() {
        // LEZIONE 185
        // RECUPERIAMO TUTTI I PROPRIETARI SALVATI IN TABELLA
        Set<Owner> ownerSet = ownerMapService.findAll();
        System.out.println("Start Verify findAll Owner " + ownerSet.size() );
        // VERIFICHIAMO CHE IL PROPRIETARIO IN TABELLA SIA UNO
        assertEquals(1, ownerSet.size());
        System.out.println("End Verify findAll Owner " + ownerSet.size() );
    }

    @Test
    void findById() {
        Owner owner = ownerMapService.findById(ownerId);
        System.out.println("Start Verify findById Owner " + ownerId + " " + owner.getId()  );
        assertEquals(ownerId, owner.getId());
        System.out.println("End Verify findById Owner " + ownerId + " " + owner.getId()  );
    }

    @Test
    void saveExistingId() {
        Long id = 2L;

        Owner owner2 = Owner.builder().id(id).build();

        System.out.println("Start saveExistId Owner " + id );
        Owner savedOwner = ownerMapService.save(owner2);

        System.out.println("End saveExistId Owner " + id );
        System.out.println("Start Verify saveExistId Owner " + id + " " + savedOwner.getId());

        assertEquals(id, savedOwner.getId());
        System.out.println("End Verify saveExistId Owner " + id + " " + savedOwner.getId());

    }

    @Test
    void saveNoId() {

        // VErifico che esista l'ID anche se non lo specifico
        Owner savedOwner = ownerMapService.save(Owner.builder().build());
        System.out.println("Start VerifyOwnerNotNull saveNoId Owner " );
        assertNotNull(savedOwner);
        System.out.println("End VerifyOwnerNotNull save Owner " + savedOwner.getId());
        System.out.println("Start VerifyIdNotNull save Owner " + savedOwner.getId());
        assertNotNull(savedOwner.getId());
        System.out.println("End VerifyIdNotNull save Owner " + savedOwner.getId());
    }

    @Test
    void delete() {
        // CONTROLLO CHE LA MAPPA SIA VUOTA
        // DOPO LA CANCELLAZIONE
        // BISOGNA SEMPRE FARE ATTENZIONE CHE AD OGNI METODO
        // VIENE PRIMA CHIAMATO IL METODO SETUP CHE CREA
        // UN SINGOLO RECORD
        // CANCELLO IL PROPRIETARIO IL CUI ID
        // VIENE RITORNATO DAL METODO findById
        ownerMapService.delete(ownerMapService.findById(ownerId));
        System.out.println("Start verify delete  Owner " +  ownerId);

        assertEquals(0, ownerMapService.findAll().size());
        System.out.println("End verify delete  Owner ");
    }

    @Test
    void deleteById() {
        // PRIMA CANCELLAVO L'OGGETTO QUI CANCELLO
        // A PARTIRE DAL VALORE DI ID
        ownerMapService.deleteById(ownerId);
        System.out.println("Start verify deleteById  Owner " +  ownerId);

        assertEquals(0, ownerMapService.findAll().size());
        System.out.println("End verify deleteById  Owner " +  ownerId);
    }

    @Test
    void findByLastName() {
        Owner smith = ownerMapService.findByLastName(lastName);
        System.out.println("Start verifyNotNull findByLastName  Owner " +  lastName);
        assertNotNull(smith);
        System.out.println("End verifyNotNull findByLastName  Owner " +  lastName);

        System.out.println("Start verifyId findByLastName  Owner " +  smith.getId());
        Long localOwnerId = 3L;
        boolean findOk = true ;
        if (findOk)
            assertEquals(ownerId, smith.getId());
        else
            assertEquals(localOwnerId, smith.getId());

        System.out.println("End verifyId findByLastName  Owner " +  smith.getId());

    }

    @Test
    void findByLastNameNotFound() {
        Owner smith = ownerMapService.findByLastName("foo");
        System.out.println("Start verifyNull findByLastNameNotFound  Owner foo " );

        assertNull(smith);
        System.out.println("End verify findByLastNameNotFound  Owner foo " );
    }
}