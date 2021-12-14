package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.*;
import guru.springframework.sfgpetclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * Created by jt on 7/25/18.
 * // LEZIONE 123
 * // LEZIONE 150
 */
@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    // LEZIONE 123 aggiungo il servizio alla classe
    private final PetTypeService petTypeService;
    // LEZIONE 123 aggiungo il servizio alla classe
    // LEZIONE 126 aggiungo il servizio alla classe
    private final SpecialtyService specialtyService;
    // LEZIONE 126 aggiungo il servizio alla classe
    // LEZIONE 158 aggiungo il servizio alla classe
    private final VisitService visitService;
    // LEZIONE 158 aggiungo il servizio alla classe

    // LEZIONE 123 e 158 aggiungo il servizio al costruttore della classe
    // in modo che Spring lo carica attraverso il
    // meccanismo di inversione del controllo
    // Quando questo component si avvia
    // In questo modo Spring Collegherà un oggetto
    //  OwnerService VetService PetTypeService SpecialtyService VisitService
    // che potra' essere usato nel metodo loadData
    // per Salvare un oggetto di tipo PetType
    // Dopo che è stato iniettato in base al profilo attivo
    public DataLoader(OwnerService ownerService,
                      VetService vetService,
                      PetTypeService petTypeService,
                      SpecialtyService  specialtyService,
                      VisitService visitService) {

        // LEZIONE 124
        this.ownerService = ownerService;
        // LEZIONE 125
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
        // LEZIONE 125
        // LEZIONE 158
        this.visitService = visitService;
        // LEZIONE 158
    }

    @Override
    public void run(String... args) throws Exception {

        int count = petTypeService.findAll().size();
        // Ci prepariamo a farlo funzionare con Jpa e MySql
        // Quindi non devo eseguire due volte
        // il caricamento di dati se l'ho gia' fatto
        System.out.println("Start run count test...." + count);

        if (count == 0 ){
            loadData();
        }
    }

    private void loadData() {
        // LEZIONE 123
        // IStanzio oggetti tipi di animali
        // che saranno usati piu avanti
        // per definire degli animali veri e propri
        System.out.println("Start Loaded PetType....");
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);
        System.out.println("End Loaded ...." + savedDogPetType.getName());
        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);
        System.out.println("End Loaded ...." + savedCatPetType.getName());
        System.out.println("End Loaded PetType....");
        // LEZIONE 123

        // LEZIONE 126
        System.out.println("Start Loaded Speciality....");
        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        // Persisto la specializzazione sulla mappa
        // ottenendo un determinato un valore di ID
        Speciality savedRadiology = specialtyService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        // Persisto la specializzazione sulla mappa
        // ottenendo un determinato un valore di ID
        Speciality savedSurgery = specialtyService.save(surgery);

        Speciality dentistry = new Speciality();
        dentistry.setDescription("dentistry");
        // Persisto la specializzazione sulla mappa
        Speciality savedDentistry = specialtyService.save(dentistry);
        System.out.println("End Loaded Speciality....");
        // LEZIONE 126

        // LEZIONE 124
        System.out.println("Start Loaded Owner1....");
        Owner owner1 = new Owner();
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");
        owner1.setAddress("123 Brickerel");
        owner1.setCity("Miami");
        owner1.setTelephone("1231231234");
        System.out.println("End Loaded Owner1...." + owner1.getFirstName());

        System.out.println("Start Loaded Pet of ...." + owner1.getFirstName());

        Pet mikesPet = new Pet();
        mikesPet.setPetType(savedDogPetType);
        mikesPet.setOwner(owner1);
        mikesPet.setBirthDate(LocalDate.now());
        mikesPet.setName("Rosco");
        // Creo un associazione tra owner1 e il suo
        // animale domestico
        owner1.getPets().add(mikesPet);
        System.out.println("End Loaded Pet of ...." + owner1.getFirstName());
        // Persisto il proprietario sulla mappa
        // ottenendo un determinato un valore di ID
        ownerService.save(owner1);
        System.out.println("End ServiceSave Owner1...." + owner1.getFirstName());

        // LEZIONE 168
        // SOLO X TEST PROVO AD USARE LE POSSIBLITA
        // FORNITE DALL'ANNOTAZIONE Builder
        // Piuttosto che usare tutte queste chiamate
        // a metodi setter potrei usufruire una chiamata
        // ad un builder che significa costruttore
        // dove specifico il campo da settare come
        // ad esempio l'indirizzo
        // La cosa migliore è aggiungere questa tecnica
        // all'interno di un costruttore AllArgsCostr oppure NoArgsCostr
        // Siccome stiamo estendendo da persona
        // nel costruire un oggetto Owner
        /* System.out.println("Start Loaded PetType....");
        Owner.builder().address("Prova da Builder").build();
        System.out.println("End Loaded PetType....");
        */
        // LEZIONE 168

        System.out.println("Start Loaded Owner2....");
        Owner owner2 = new Owner();
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenanne");
        owner2.setAddress("123 Brickerel");
        owner2.setCity("Miami");
        owner2.setTelephone("1231231234");
        System.out.println("End Loaded Owner2...." + owner2.getFirstName());

        System.out.println("Start Loaded Pet of ...." + owner2.getFirstName());

        Pet fionasCat = new Pet();
        fionasCat.setName("Just Cat");
        fionasCat.setOwner(owner2);
        fionasCat.setBirthDate(LocalDate.now());
        fionasCat.setPetType(savedCatPetType);
        // Creo un associazione tra owner2 e il suo
        // animale domestico
        owner2.getPets().add(fionasCat);
        System.out.println("End Loaded Pet of ...." + owner2.getFirstName());
        // Persisto il proprietario sulla mappa
        // ottenendo un determinato un valore di ID
        ownerService.save(owner2);
        System.out.println("End ServiceSave Owner2...." + owner2.getFirstName());
        // LEZIONE 124

        // LEZIONE 158
        System.out.println("Start ServiceSave catVisit....pet " + fionasCat.getName() + " owner " + fionasCat.getOwner().getFirstName());
        Visit catVisit = new Visit();
        catVisit.setPet(fionasCat);
        catVisit.setDate(LocalDate.now());
        catVisit.setDescription("Sneezy Kitty");

        visitService.save(catVisit);
        System.out.println("End ServiceSave catVisit...." + catVisit.getDescription());
        // LEZIONE 158

        System.out.println("End Loaded Owners....");

        // LEZIONE 126
        System.out.println("Start Loaded Vet....");
        System.out.println("Start Loaded Vet1....");
        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        // IL VETTORE DEVE ESSERE INIZIALIZZATO
        // NELLA CLASSE VET
        vet1.getSpecialities().add(savedRadiology);
        System.out.println("End Loaded Specialities of Vet1...." + vet1.getFirstName());
        // Persisto il veterinario sulla mappa
        // ottenendo un determinato un valore di ID
        vetService.save(vet1);
        System.out.println("End ServiceSave Vet1...." + vet1.getFirstName());

        System.out.println("Start Loaded Vet2....");
        Vet vet2 = new Vet();
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");
        // IL VETTORE DEVE ESSERE INIZIALIZZATO
        // NELLA CLASSE VET
        vet2.getSpecialities().add(savedSurgery);
        System.out.println("End Loaded Specialities of Vet2...." + vet2.getFirstName());
        // Persisto il veterinario sulla mappa
        // ottenendo un determinato un valore di ID
        vetService.save(vet2);
        System.out.println("End ServiceSave Vet2...." + vet2.getFirstName());
        System.out.println("End Loaded Vet....");
        // LEZIONE 126

        System.out.println("Loaded Vets....");
    }
}
