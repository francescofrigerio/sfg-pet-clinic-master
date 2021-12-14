package guru.springframework.sfgpetclinic.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by jt on 7/29/18.
 * LEZIONE 149 AGGIUNTA ANNOTAZIONE ENTITY
 *  EREDITA DIRETTAMENTE
 *  DA BASEENTTY CHE E' UNA SUPERCLASS DI MAPPE
 *  DA CUI VIENE EREDITATO IL CAMPO ID
 *  PER CUI NON DOBBIAMO IMPOSTARE NIENTE
 *  A PROPOSITO DEL CAMPO CHIAVE ID
 *  PER CUI STIAMO USANDO IL TIPO LONG
 *  DICHIARO IL NOME DELLA TABLE DEL DB ASSOCIATO
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
@Builder
// LEZIONE 168 Aggiungo annotazioni lombok
@Entity
@Table(name = "visits")
public class Visit extends BaseEntity {

    // LEZIONE 149 ANNOTIAMO LA COLONNA DELLA DATA
    // DELLA VISITA
    @Column(name = "date")
    // LEZIONE 119 Aggiunta della data locale
    private LocalDate date;

    // LEZIONE 149 ANNOTIAMO LA COLONNA
    // DELLA DESCRIZIONE
    // DELLA VISITA
    @Column(name = "description")
    // LEZIONE 119 AGGIUNTA DELLA DESCRIZIONE
    private String description;

    // LEZIONE 149
    // IMPOSTIAMO MAPPATURA E RELAZIONE MANYTOONE
    // TRA LE CLASSI DI OGGETTI VISITE E ANINALI
    // DICHIARO L'OGGETTO pet
    // CHE HA UNA RELAZIONE CON LE VISITE
    // DEL TIPO CHE UN ANIMALE PUO' AVERE
    // TANTE VISITE. MA UNA VISITA PUO AVERE
    // UN SOLO ANIMALE
    // IL NOME DELL'OGGETTO
    // per DEVE ESSERE USATO NEL NOME DEL
    // CAMPO DA MAPPARE SUll'ALTRO LATO
    // DELLA RELAZIONE CIOE' NELL CLASSE PET
    // SISTEMA LA MAPPATURA ALL'INVERSO
    // RISPETTO ALLA VERA DEFINIZIONE
    // DELLA RELAZIONE CHE SI TROVA
    // NELLA CLASSE PET
    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    // LEZIONE 119 Aggiunta della data locale
    public LocalDate getDate() {
        return date;
    }

}
