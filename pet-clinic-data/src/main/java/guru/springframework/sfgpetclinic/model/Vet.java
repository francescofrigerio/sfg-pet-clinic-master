package guru.springframework.sfgpetclinic.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by jt on 7/13/18.
 * LEZIONE 148 AGGIUNTA ANNOTAZIONE ENTITY
 * EREDITA DA PERSON
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
@Table(name = "vets")
public class Vet extends Person {

    // LEZIONE 126 VETTORE Specialty inizializzato
    // Altrimenti potrebbe andare in errore
    // nella chiamata di getSpecialities in bootstrap
    // LEZIONE 148
    // DICHIARO LA RELAZIONE MANYTOMANY
    // A TANTI VETERINARI CORRISPONDONO
    // TANTE SPEICIALITA
    // SPECIFICHIANO IL TIPO DI RECUPERO
    // DEI DATI IN EAGER QUINDI CON LE
    // QUERY RECUPERO I DATI DI ENTRAMBE
    // LE TABELLE TUTTO IN UNA VOLTA
    // MENTRE DI DEFAULT LE
    // RELAZIONI SONO IMPOSTATE A LAZY
    // CHE INVECE CARICA I DATI SONO
    // CARICATI SOLO QUANDO SONO RICHIESTI
    // E LE ENTITA' VETERINARIO E SPECIALITA'
    // SAREBBERO NULLE SE NON LO FACESSIMO
    // DOPO LA SPECIFICA DEL FETCH TYPE
    // SPECIFICHIAMO IL NOME DELLA RELAZIONE vet_specialties
    // LE TABELLE IN JOIN
    // E LE COLONNE CHE PERMETTONO DI METTERE
    // IN JOIN LE TABELLE SIA LATO VETERINARI
    // TRAMITE L'ANNOTAZIONE joinColumns
    // SIA LATO SPECIALITA TRAMITE
    // L'ANNOTAZIONE inverseJoinColumns
    // LE ENITA' VETERINARI E SPECIALITA'
    // AVRANNO UNA RELAZIONE MA IL DATABASE
    // SOTTOSTANTE AVRA' LE TABELLE CHE
    // DEFINIRA' GLI ID SU ENTRAMBI I LATI
    // DELLA RELAZIONE
    // QUINDI LA RELAZIONE UNISCE LE DUE TABELLE
    // E QUESTO OFFRE LA PERSISTENZA IN MODO
    // CHE JPA SAPPIA COME NAVIGARE TRA LE DUE
    // TABELLE E COME LA RELAZIONE E'
    // EFFETTIVAMENTE DEFINITA A LIVELLO DI DB
    //
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "vet_specialties",
            joinColumns = @JoinColumn(name = "vet_id"),
            inverseJoinColumns = @JoinColumn(name = "speciality_id"))
    private Set<Speciality> specialities = new HashSet<>();
    // LEZIONE 126 VETTORE Specialty inizializzato

    // LEZIONE 120 AGGIUNTA RELAZIONE CON LA SPECIALITA
    // DEL VETERINARIO
    // LEZIONE 124 AGGIUNTA INIZIALIZZAZIONE
    // IN MODO CHE NON VADA IN ERRORE PERCHE' LO TROVA NULL
    // QUANDO VIENE AGGIUNTO IN DATALOADER
    // ALLA PRIMA ASSOCIAZIONE DI UNA SPECIALITA
    // DEL VETERINARIO

}
