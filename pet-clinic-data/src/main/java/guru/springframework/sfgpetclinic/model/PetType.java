package guru.springframework.sfgpetclinic.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by jt on 7/13/18.
 * LEZIONE 147 DICHIARAZIONE COME ENTITY JPA
 * DICHIARO IL NOME DELLA TABLE DEL DB ASSOCIATO
 */
// LEZIONE 168 Aggiungo annotazioni lombok
// Possono essere eliminati i metodi getter
// e setter dei campi
// Lo stesso vale per il costruttore senza argomenti
// Il miglior modo per controllare se tutto è a posto
// è controllare nella classe Bootstrap
// se ci sono problemi nel refeenziare
// e poi anche runtime se non ci sono problemu
// dopo aver aggiunto modificato annotazioni lombok
// gli oggetti del pkg model
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
// LEZIONE 168 Aggiungo annotazioni lombok
@Entity
@Table(name = "types")
public class PetType extends BaseEntity {

    @Builder
    public PetType(Long id, String name) {
        super(id);
        this.name = name;
    }

    // LEZIONE 147 DICHIARAZIONE
    // DEI NOMI DELLE COLONNE PER LE PROPRIETA
    @Column(name = "name")
    private String name;


    // LEZIONE 214 Viene aggiunto
    // il metodo toString per allinearsi
    // al progetto template di Spring
    // da cui sono state prese le pagine web
    // altrimenti non si vede nella pagina OwnersDetail
    // Il nome dell'oggetto PetType
    @Override
    public String toString() {

        return name;
    }
}
