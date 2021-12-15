package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.*;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// LEZIONE 187
// Sostituzione di RunWith e classe SpringRunner
// con l'annotazione ExtendWith e MockitoExtension
@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    // Il Nostro OwnerCOntroller di Test
    // avra' bisogno di un Servizio dedicato
    @Mock
    OwnerService ownerService;

    // Ecco Il Nostro OwnerCOntroller di Test
    // InjectMocks permette d'inizializzare nel setup
    // Gli oggetti annotati con Mock
    @InjectMocks
    OwnerController controller;

    // Una lista set di Owner per fare i Test
    // da inizializzare nel setup
    Set<Owner> owners;

    MockMvc mockMvc;

    // Eseguito prima di ogni test
    // in modo da inizializzera
    // gli oggetti dichiarati Beffardi
    @BeforeEach
    void setUp() {
        // ALLOCHIAMO LA MEMORIA
        // E AGGIUNGIAMO UN PAIO DI PROPRIETARI
        // SUL SET CHE SARANNO USATI NEI DIVERSI SET
        owners = new HashSet<>();
        owners.add(Owner.builder().id(1l).build());
        owners.add(Owner.builder().id(2l).build());

        // CREO CON build UN ISTANZA ED INIZIALIZZO SPRING MVC
        // VERSIONE STANDALONE OPZIONE PIU' LEGGERA
        // PASSANDO IN INPUT
        // L'OGGETTO CONTROLLARE CHE DEVE
        // ESSERE ANNOTATO CON InjectMocks
        // mockMvc è un progetto di Spring
        // da usare con i controller che possono
        // essere complicati da testare
        // Ci sono molte cose che si possono
        // fare con standaloneSetup come ad esempio
        // la configurazione e altre opzioni
        // In questo modo inizializziamo un ambiente
        // simulato per il controller in modo da poter
        // testare tutte le funzionalita da esso esposte
        // e le diverse interazioni con esso
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
    }

    @Test
    void listOwners() throws Exception{

        // PEr testare il metodo listOwners
        // del Mock Controller devo passare in input
        // Un oggetto model che è aspettato per entrare
        // nello stesso metodo della classe OwnerController
        // Dichiaro l'oggetto ritornato in caso di
        // chiamate al metodo findAll sul servizio
        when(ownerService.findAll()).thenReturn(owners);

        // Interazione fittizia
        // Verifico che lo stato del ritorno sia OK=http status 200
        // e non Ko=http status 400
        // L'oggetto Matcher consente
        // tutta una serie d'importazioni statiche
        // come view che permette di controllare
        // il nome ritornato che mi aspetto sia la stringa in input
        // infine mi assicuro di avere un attributo
        // chiamato owners che una dimesione di due elementi
        //mockMvc.perform(get("/owners/list"))
        mockMvc.perform(get("/owners/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/index"))
                .andExpect(model().attribute("owners",hasSize(2)));

    }

    @Test
    void listOwnersByIndex() throws Exception{

        when(ownerService.findAll()).thenReturn(owners);

        mockMvc.perform(get("/owners/index"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/index"))
                .andExpect(model().attribute("owners",hasSize(2)));

    }

    @Test
    void findOwners() throws Exception {


        mockMvc.perform(get("/owners/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/findOwners"))
                .andExpect(model().attributeExists("owners"));

        // Controllo che non ci siano interazioni
        // perche' questo metodo non dovrebbe interagire
        // con il servizio proprietari di Mock
        verifyZeroInteractions(ownerService);
    }

    @Test
    void processFindFormReturnMany() throws Exception {
        when(ownerService.findAllByLastNameLike(anyString()))
                .thenReturn(Arrays.asList(Owner.builder().id(1l).build(),
                        Owner.builder().id(2l).build()));

        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/ownersList"))
                .andExpect(model().attribute("selections", hasSize(2)));
    }

    @Test
    void processFindFormReturnOne() throws Exception {
        when(ownerService.findAllByLastNameLike(anyString())).thenReturn(Arrays.asList(Owner.builder().id(1l).build()));

        mockMvc.perform(get("/owners"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"));
    }

    @Test
    void processFindFormEmptyReturnMany() throws Exception {
        when(ownerService.findAllByLastNameLike(anyString()))
                .thenReturn(Arrays.asList(Owner.builder().id(1l).build(),
                        Owner.builder().id(2l).build()));

        mockMvc.perform(get("/owners")
                        .param("lastName",""))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/ownersList"))
                .andExpect(model().attribute("selections", hasSize(2)));;
    }

    @Test
    void displayOwner() throws Exception {
        when(ownerService.findById(anyLong())).thenReturn(Owner.builder().id(1l).build());

        mockMvc.perform(get("/owners/123"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/ownerDetails"))
                .andExpect(model().attribute("owner", hasProperty("id", is(1l))));
    }


    @Test
    void initCreationForm() throws Exception {
        mockMvc.perform(get("/owners/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/createOrUpdateOwnerForm"))
                .andExpect(model().attributeExists("owner"));

        verifyZeroInteractions(ownerService);
    }

    @Test
    void processCreationForm() throws Exception {
        when(ownerService.save(ArgumentMatchers.any())).thenReturn(Owner.builder().id(1l).build());

        mockMvc.perform(post("/owners/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"))
                .andExpect(model().attributeExists("owner"));

        verify(ownerService).save(ArgumentMatchers.any());
    }

    @Test
    void initUpdateOwnerForm() throws Exception {
        when(ownerService.findById(anyLong())).thenReturn(Owner.builder().id(1l).build());

        mockMvc.perform(get("/owners/1/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/createOrUpdateOwnerForm"))
                .andExpect(model().attributeExists("owner"));

        verifyZeroInteractions(ownerService);
    }

    @Test
    void processUpdateOwnerForm() throws Exception {
        when(ownerService.save(ArgumentMatchers.any())).thenReturn(Owner.builder().id(1l).build());

        mockMvc.perform(post("/owners/1/edit"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"))
                .andExpect(model().attributeExists("owner"));

        verify(ownerService).save(ArgumentMatchers.any());
    }
}