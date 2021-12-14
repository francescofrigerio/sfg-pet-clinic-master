package guru.springframework.sfgpetclinic.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by jt on 7/13/18.
 * LEZIONE 147 DICHIARAZIONE COME ENTITY JPA
 * OWNER E' DICHIARATO COME DI TIPO Entity
 * DICHIARO IL NOME DELLA TABLE DEL DB ASSOCIATO
 *
 */
// LEZIONE 168 Aggiungo annotazioni lombok
    // Possono essere eliminati i metodi getter
    // e setter dei campi id address city ecc.
    // Lo stesso vale per il costruttore senza argomenti
    // Il miglior modo per controllare se tutto è a posto
    // è controllare nella classe Bootstrap
    // se ci sono problemi nel refeenziare
    // gli oggetti del pkg model
    // e poi anche runtime se non ci sono problemu
    // dopo aver aggiunto modificato annotazioni lombok
@Setter
@Getter
@NoArgsConstructor
// LEZIONE 168 Aggiungo annotazioni lombok
@Entity
@Table(name = "owners")
public class Owner extends Person {

    // LEZIONE 168 ANNOTAZIONE BUILDER
    // DIRETTAMENTE SUL COSTRUTTORE IL BUILDER
    // GENERERA' TUTTI I CAMPI PRESENTI IN INPUT
    // DI CUI I PRIMI TRE id fname lname DERIVANO
    // DALLA CLASSE BASE PERSONA PER CUI SI HA BISOGNO
    // DI UN COSTRUTTORE PER QUESTI TRE CAMPI
    // E POI  ANCHE PER LA CLASSE BASE
    @Builder
    public Owner(Long id, String firstName, String lastName, String address, String city,
                 String telephone, Set<Pet> pets) {
        super(id, firstName, lastName);
        this.address = address;
        this.city = city;
        this.telephone = telephone;

        if(pets != null) {
            this.pets = pets;
        }
    }

    // LEZIONE 147 DICHIARAZIONE
    // DEI NOMI DELLE COLONNE PER LE PROPRIETA
    @Column(name = "address")
    // LEZIONE 121
    private String address;

    // LEZIONE 147 DICHIARAZIONE
    // DEI NOMI DELLE COLONNE PER LE PROPRIETA
    @Column(name = "city")
    // LEZIONE 121
    private String city;

    // LEZIONE 147 DICHIARAZIONE
    // DEI NOMI DELLE COLONNE PER LE PROPRIETA
    @Column(name = "telephone")
    // LEZIONE 121
    private String telephone;

    // LEZIONE 147 RELAZIONE ONETOMANY
    // OGNI PROPRIETARIO PUO'AVERE PIU'
    // ANIMALI
    // MAPPATA RISPETTO ALL'OGGETTO
    // owner NELLA CLASSE PET COLLEGATA
    // IMPOSTO CASCADE TYPE IN MODO
    // DA AGGIORNARE GLI OGGETTI PET
    // INSIEME ALL'OGGETTO OWNER COLLEGATO
    // SE VIENE CANCELLATO IL PROPRIETARIO
    // SONO CANCELLATI ANCHE I RELATIVI
    // ANIMALI DOMESTICI
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    // LEZIONE 119 Aggiunta animale del proprietario
    // AGGIUNTA UNA RELAZIONE
    // LEZIONE 124 AGGIUNTA INIZIALIZZAZIONE
    // IN MODO CHE NON VADA IN ERRORE PERCHE' LO TROVA NULL
    // QUANDO VIENE AGGIUNTO IN DATALOADER
    // ALLA PRIMA ASSOCIAZIONE DI UN ANIMALE
    // AL SUO PROPRIETARIO
    private Set<Pet> pets = new HashSet<>();


    /**
     * Return the Pet with the given name, or null if none found for this Owner.
     *
     * @param name to test
     * @return true if pet name is already in use
     */
    // LEZIONE 168 SI PUO ELIMINARE
    /* public Pet getPet(String name) {

        return getPet(name, false);
    }
    */
    /**
     * Return the Pet with the given name, or null if none found for this Owner.
     *
     * @param name to test
     * @return true if pet name is already in use
     */
    public Pet getPet(String name, boolean ignoreNew) {
        name = name.toLowerCase();
        for (Pet pet : pets) {
            if (!ignoreNew || !pet.isNew()) {
                String compName = pet.getName();
                compName = compName.toLowerCase();
                if (compName.equals(name)) {
                    return pet;
                }
            }
        }
        return null;
    }

}
