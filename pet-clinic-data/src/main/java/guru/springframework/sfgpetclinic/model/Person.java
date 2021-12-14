package guru.springframework.sfgpetclinic.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Created by jt on 7/13/18.
 * LEZIONE 147
 * ANCHE QUI VIENE AGGIUNTA MappedSuperclass
 * SIAMO SICURI CHE NON LAVOREMO MAI
 * DIRETTAMENTE CON QUESTA CLASSE
 * VISTO CHE AVRA CLASSI FIGLIE COME OWNER
 * E AL TEMPO
 * STESSO EREDITA DA BASEENTIY C
 */
// LEZIONE 168 Aggiungo annotazioni lombok
    // Buona cosa aggiungere le annotazioni
    // getter and setters anche alle classi intermedie
    // Bisogna fare attenzione perchè il comportamento
    // non è sempre quello desiderato
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
// LEZIONE 168 Aggiungo annotazioni lombok
@MappedSuperclass
public class Person extends BaseEntity {

    // LEZIONE 168 ANNOTAZIONE BUILDER OWNER
    // AGGIUNGO COSTRUTORE PER I TRE CAMPI
    // USATI NELLA CLASSE OWNER
    public Person(Long id, String firstName, String lastName) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
    }
    // LEZIONE 168 ANNOTAZIONE BUILDER OWNER

    // LEZIONE 147
    // Imposta l'annotazione della colonna
    // e la  convenzione di denominazione
    // per la colonna del DB ci dice quindi
    // quello che ci aspettiamo dalle colonne del DB
    // Annotazione @NotEmpty
    // significa che ci si aspetta
    // che quel campo non sia mai vuoto
    // Al momento non vengono aggiunti vincoli
    // per non complicare il progetto
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

}
