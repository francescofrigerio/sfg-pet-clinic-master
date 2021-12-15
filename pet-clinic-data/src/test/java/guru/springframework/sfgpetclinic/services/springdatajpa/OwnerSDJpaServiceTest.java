package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repositories.OwnerRepository;
import guru.springframework.sfgpetclinic.repositories.PetRepository;
import guru.springframework.sfgpetclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

// LEZIONE 186
// Esempio di test sui JpaService
// che usano repository mediante Mockito
// Evidenziao qui due modi per testare una classe
// uno più leggero e uno meno
// Thompson preferisce quello leggero mentre
// l'altro usa i profili come una molla
// x visualizzare un database H2
// con la controindicazione di aprire lo Spring Context
// e far durare il test molto più a lungo
// ma con il vantaggio di testare lo strato
// di persistenza del dato sul DB
// Quindi dipende dal caso d'uso particolare
// Thompson consiglia di tendere a preferire
// il test leggero e nel caso si abbia bisogno
// di testare qualcosa a livello di persistenza
// farlo solo in caso di un bisogno esplicito
// ed è quindi un bene farlo
// In generale è però preferibile avere un test leggero
// in modo da non appesantire la procedura nel suo complesso
// Vediamo come impostare il codice di Mockito
// Inizio con tasto + generate + test sul nome della classe
// Stiamo usando Junit5 quindi usiamo
// l'annotazione ExtendWith al postom di RunWith
// per effettuare il test d'integrazione
// La classe in input all'annotazione non sara' ovviamente SpringRunner
// usata con RunWith ma la classe con l'estensione di Mockito
// In questo impostiamo l'ambiente JUnit per Mockito
// Serve anche include la dipendenza mockito-junit-jupiter
// nel pom.xml del progetto data
@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

    public static final String LAST_NAME = "Smith";
    // LEZIONE 186
    // La classe da testare come si vede ha dei repository
    // che sono istanziati nel costruttore Per cui li dichiaro
    // come Oggetti della classe che dichiaro non privati
    // per essere più comodi
    // e li vado a testare in modo
    // da testare quanto viene fatto anche nella classe
    // taggandoli con l'annotazione Mock
    // IntellJ e Mockito insieme dovrebbe essere abbastanza
    // intelligenti da iniettare le istanze deim repository
    // grazia alla sola presenza dell'annotazione Mock
    @Mock
    OwnerRepository ownerRepository;

    @Mock
    PetRepository petRepository;

    @Mock
    PetTypeRepository petTypeRepository;

    // LEZIONE 186
    // dichiariamo un servizio Jpa del proprietario
    @InjectMocks
    OwnerSDJpaService service;

    // LEZIONE 186
    // dichiariamo un proprietario
    // istanziato prima di ogni metodo
    // all'interno di setup()
    Owner returnOwner;


    // Tutto ciò che viene annotato come test
    // viene preceduto dalla chiamata di questo metodo
    // In modo che l'oggetto returnOwner venga
    // reinizializzato ad ogni test
    // In modo che ad ogni test , per sicurezza , si riparte
    // da zero e un test non dipende da quanto fa un altro
    // Non genero dipendenze tra i diveri test
    // Non è un esigenza specifica in questo ma solo
    // una pratica consigliabile (best praticse)
    // e una buona abitudine
    @BeforeEach
    void setUp() {

        System.out.println("setUp start creazione Owner " + LAST_NAME);
        returnOwner = Owner.builder().id(1l).lastName(LAST_NAME).build();
    }

    // Conviene eseguire e testare
    // ogni test subito dopo l'implentazione
    // in modo da non arrivare alla fine
    // e vedere che no
    @Test
    void findByLastName() {
        // Con impostazioni statiche come any() è possibile
        // inciampare sopratutto con Eclispe che è meno
        // sofisticato e intelligente di IntelliJ
        // Quindi quando viene chiamato il metodo
        // findByLastName del repository dei proprietari
        // con qualsiasi valore in input mette ritorna
        // la proprieta locale alla classe returnOwner
        // che potra poi essere verificato nelle righe seguenti
        // Dopo questa dichiarazione returnOwner è un finto
        // repository di proprietari gestito da Mockito
        when(ownerRepository.findByLastName(any())).thenReturn(returnOwner);

        // Verifico che tutti i Mock iniettati funzionino
        // correttamente
        // Senza fare nulla di particolare se tutto
        // funziona significa che tutti i repository
        // annotati con Mock sono iniettati e il servizio
        // e' stato creato perche' se il servizio
        // non fosse iniettato da Mockito avremmo
        // un errore NPE su un puntatore NullPointerException
        // Si puo verificare commentanto l'annotazione
        // InjectMocks del servizio e vedere che il mancato
        // avvio del metodo è dovuto a quello
        // eseguendo il metodo findByLastName
        // andiamo a testare il repositiry del proprietario
        Owner smith = service.findByLastName(LAST_NAME);
        System.out.println("findByLastName start found Owner " + smith.getLastName());
        // Controllo che il valore contenuto da smith
        // corrisponda al valore in input statico
        assertEquals(LAST_NAME, smith.getLastName());

        // E' un po ridondante ma solo per mostrare
        // che è possibile testare in diversi modi
        // e come fare le verifiche di interazioni simulate
        // con Mockito
        // Così verifico un input generico
        verify(ownerRepository).findByLastName(any());
    }

    @Test
    void findAll() {
        // Creo un nuovo HashSet aggiungendo 2 proprietari
        Set<Owner> returnOwnersSet = new HashSet<>();
        returnOwnersSet.add(Owner.builder().id(1l).build());
        returnOwnersSet.add(Owner.builder().id(2l).build());

        // Per ogni chiamata al metodo findAll ritorna questo
        // nuovo HashSet impostato
        // Dopo questa dichiarazione questo HashSet è un finto
        // repository di proprietari gestito da Mockito
        when(ownerRepository.findAll()).thenReturn(returnOwnersSet);

        // A questo punto chiamando findAll
        // invoco la derisione di Mockito(beffardo)
        // tramite questo finto repository
        // e dovrei ottenere un set di proprietari
        Set<Owner> owners = service.findAll();

        // Verifico che il Set ritornato non sia nullo
        assertNotNull(owners);
        // Verifico che il Set ritornato contenga 2 proprietari
        assertEquals(2, owners.size());
    }

    @Test
    void findById() {
        // lavoro con il repository di proprietari finto
        // e dichiaro che per ogni chiamata al metodo findById
        // che accetta in input qualsiasi long con il metodo statico
        // anylong e ritorna un Cast a Optional di returnOwner
        // Il valore ritornato da findById e' un oggetto Owner oppure null
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(returnOwner));

        Owner owner = service.findById(1L);

        // Controllo di averlo trovato
        assertNotNull(owner);
    }

    @Test
    void findByIdNotFound() {
        // A differenza del precedente
        // Per qualsiasi chiamata ritorna un oggetto vuoto
        // come per dire che nel repository non è stato trovato
        // nulla
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());

        Owner owner = service.findById(1L);

        // Controllo di NON averlo trovato
        assertNull(owner);
    }


    @Test
    void save() {
        Owner ownerToSave = Owner.builder().id(1L).build();

        // Quando salvo qualcosa sul finto repository
        // ritorno il valore ownerToSave
        when(ownerRepository.save(any())).thenReturn(returnOwner);

        Owner savedOwner = service.save(ownerToSave);

        // Affermo che il valore in input non è nullo
        // in questo modo mi assicuro solo che sia stato
        // chiamato il metodo save del repository
        assertNotNull(savedOwner);

        // Verifico il salvataggio di qualsiasi Owner
        // sul repository
        // aggiungo un doppio controllo se vogliamo
        // facoltativo ma solo per mostrare
        // come utilizzare il metodo
        // per assicurarsi che abbia fatto una interazione
        // simulata
        verify(ownerRepository).save(any());
    }

    @Test
    void delete() {
        // ritorna un valore void
        // e interagisce con un oggetto
        // iniettato che è il reposiory di Owner
        service.delete(returnOwner);

        // Non ritornando nessun valore
        // non è possibile affermare nulla
        // ed quindi e' molto importante effettuare una verifica
        // cioè che il metodo delete del repository sia
        // chiamato una volta e verificare il tempo finto
        // del reposioty finto
        // Verifichiamo la simulazione e il numero di volte
        //default is 1 times
        verify(ownerRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        // ritorna un valore void
        // e interagisce con un oggetto
        // iniettato che è il reposiory di Owner
        service.deleteById(1L);

        // Verifichiamo anche qui come nel metodo
        // precedente la simulazione
        // Impostazione predefinita della sintassi
        // di verifica
        // Se dovessimo verificarlo piu volte
        // potremmo specificarlo ma in questo
        // caso lo facciamo solo una volta e non serve
        // perche 1 è il default
        verify(ownerRepository).deleteById(anyLong());
    }
}