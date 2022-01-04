package guru.springframework.sfgpetclinic.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by jt on 7/13/18.
 * LEZIONE 147 DICHIARAZIONE COME ENTITY JPA
 * EREDITA DIRETTAMENTE
 * A BASEENTTY CHE E' UNA SUPERCLASS DI MAPPE
 * DA CUI VIENE EREDITATO IL CAMPO ID
 * PER CUI NON DOBBIAMO IMPOSTARE NIENTE
 * A PROPOSITO DEL CAMPO CHIAVE ID
 * PER CUI STIAMO USANDO IL TIPO LONG
 * DICHIARO IL NOME DELLA TABLE DEL DB ASSOCIATO
 */
// LEZIONE 168 Aggiungo annotazioni lombok
// Possono essere eliminati i metodi getter
// e setter dei campi
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
@AllArgsConstructor
// LEZIONE 168 Aggiungo annotazioni lombok
@Entity
@Table(name = "pets")
public class Pet extends BaseEntity {

    @Builder
    public Pet(Long id, String name, PetType petType, Owner owner, LocalDate birthDate, Set<Visit> visits) {
        super(id);
        this.name = name;
        this.petType = petType;
        this.owner = owner;
        this.birthDate = birthDate;

        if (visits == null || visits.size() > 0 ) {
            this.visits = visits;
        }
    }

    // LEZIONE 147 DICHIARAZIONE
    // DEI NOMI DELLE COLONNE PER LE PROPRIETA
    @Column(name = "name")
    private String name;

    // LEZIONE 147
    // DEFINISCO UN PETTYPE SINGOLO
    // RELAZIONE MANYTOONE
    // METTENDO IN JOIN SULLA COLONNA SPECIFICATA
    // ANNOTAZIONE JoinColumn DICE
    // A JPA E HIBERNATE COME LE DUE
    // ENTITA SONO COLLEGATE A LIVELLO
    // DI DATABASE E CIOE' CON LA PROPRIETA ID
    // DELL'OGGETTO PetType
    // UN TIPO DI ANIMALI PUO ESSERE ASSOCIATO
    // A PIU ANIMALI
    @ManyToOne
    @JoinColumn(name = "type_id")
    private PetType petType;
    // ATTENZIONE CHE NEL PROGETTI Spring
    // originale il campo si chiama type
    // LEZIONE 214

    // LEZIONE 147
    // OGNI ANIMALE AVRA' IL SUO PROPRIETARIO
    // RELAZIONE MANYTOONE
    // METTENDO IN JOIN SULLA COLONNA SPECIFICATA
    // DI DEFAULT IL CAMPO DA <NOME_CLASSE>_ID
    // ANNOTAZIONE JoinColumn DICE
    // A JPA E HIBERNATE COME LE DUE
    // ENTITA SONO COLLEGATE A LIVELLO
    // DI DATABASE E CIOE' CON LA PROPRIETA ID
    // DEL PROPRIETARIO
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    // LEZIONE 124
    // LEZIONE 147 DICHIARAZIONE
    // DEI NOMI DELLE COLONNE PER LE PROPRIETA
    @Column(name = "birth_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    // LEZIONE 149
    // AGGGIUNGIOAMO UN NUOVO SET DI VISITE
    // RISPETTO AL QUALE
    // SPECIFICHIAMO LA MAPPATURA ANNOTANDO
    // UNA RELAZIONE OneToMany SPECIFICANDO IL CAMPO
    // DA MAPPARE E L'AGGIORNAMENTO IN CASCATA
    // INIZIALIZZATO IN MODO DA RIDURRE
    // IL CODICE IN FASE DI BOOTSTRAP
    // O EVITARE I PROBLEMI DI ECCEZZIONE
    // DOVUTE AD UN VALORE NULL DEL CAMPO
    // MENTRE INIZIALIZZANDOLO AVREMO UN
    // HASHSET VUOTO MA CON LA MEMORIA
    // INIZIALE ALLOCATA PER CUI POTREMO
    // AGGIUNGERE DEGLI ELEMENTI
    // IL CAMPO MAPPEDBY SEPIFICA L'ANIMALE
    // INSERITO ALL'INTERNO DELL'OGGETTO VISITA
    // DICHIARO L'OGGETTO pet
    // CHE HA UNA RELAZIONE CON LE VISITE
    // DEL TIPO CHE UN ANIMALE PUO' AVERE
    // TANTE VISITE. MA UNA VISITA PUO AVERE
    // UN SOLO ANIMALE
    // CASCADE SIGNIFICA CHE SE CANCELLIAMO
    // L'ANIMALE CANCELLEREMO ANCHE LA VISITA
    // COLLEGATA
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pet")
    private Set<Visit> visits = new HashSet<>();
    // ATTENZIONE AL NOME DEL CAMPO
    // DIVERSO RISPETTO AL PROGETTO
    // TEMPLATE DI SPRING
    // LEZIONE 214

}
