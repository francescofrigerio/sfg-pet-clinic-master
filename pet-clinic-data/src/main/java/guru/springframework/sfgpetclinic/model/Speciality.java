package guru.springframework.sfgpetclinic.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by jt on 7/29/18.
 * LEZIONE 148 AGGIUNTA ANNOTAZIONE ENTITY
 * EREDITA DIRETTAMENTE
 * DA BASEENTTY CHE E' UNA SUPERCLASS DI MAPPE
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
@Builder
// LEZIONE 168 Aggiungo annotazioni lombok
@Entity
@Table(name = "specialties")
public class Speciality extends BaseEntity {

    // LEZIONE 148
    // AGGIUNTA ANNOTAZIONE PER SPECIFICARE
    // IL CAMPO SUL DB
    @Column(name = "description")
    // LEZIONE 120 AGGIUNTA RELAZIONE CON LA SPECIALITA
    // DEL VETERINARIO
    private String description;

}
