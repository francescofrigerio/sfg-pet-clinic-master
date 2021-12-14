package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.services.VisitService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by jt on 8/7/18.
 * LEZIONE 157
 * ADD MAP BASED VISIT SERVICE
 * Annoto come servizio in modo che sia riconosciuto nel context
 * di Spring
 * estendiamo da AbstractMapService in modo da partire
 * da quelli e poi specializzarli
 * LEZIONE 159 UPDATE Profile to use Spring Data Jpa
 * Stiamo caricando i dati sul Db runtime creato e distrutto
 * su H2 e le varie entita sono create al suo interno
 * Non stiamo però usando i repository per cui non
 * modifichiamo alcun datoe e quindi in questa lezione
 * si vuole aggiornare i repository per poterlo fare
 */
@Service
// LEZIONE 159 SE COMMENTO QUI E IN OwnerSDJpaServic
// posso avere errori runtime
// Se non specifico un profilo attivo
// sara' attivo il profilo predefinito
// quindi voglio implementare questa classe
// solo con il profilo predefinito oppure con il profilo map
// Se si specifica un profilo attivo
// come springdatajpa questa classe non sara
// implementata nel context di Spring
@Profile({"default", "map"})
public class VisitMapService extends AbstractMapService<Visit, Long> implements VisitService {

    @Override
    // LEZIONE 157 Aggiungo la specifiica
    // del tipo di Oggetti implementato nel Set
    // restituito e chiamo il metodo della classe base
    public Set<Visit> findAll() {

        return super.findAll();
    }

    @Override
    public Visit findById(Long id) {

        // LEZIONE 157
        // chiamo il metodo della classe base
        // usando come valore identificativo un tipo Long
        return super.findById(id);
    }

    @Override
    public Visit save(Visit visit) {

        // LEZIONE 157
        // VISITA HA UNA DESCRIZIONE MA HA
        // ANCHE UN ANIMALE DOMESTICO
        // CHE E' UN OGGETTO COMPLESSO
        // PER CUI DEVO CONTROLLARE LA SUA
        // INTEGRITA' AD ESEMPIO DEVO
        // CONTROLLARE CHE ABBIA UN PROPRIETARIO
        // ABBIAMO ABBASTANZA INFORMAZIONI
        // PER MANTENERE IL PROPRIETARIO
        // IL METODO SAVE DEL PROPRIETARIO
        // E' PIU COMPLESSO PERCHE SERVE
        // CONTROLLARE BENE PRIMA DI PERSISTERE IL DATO
        // CONTROLLO QUINDI PRIMA DI SALVARE LA VISITA
        // SE l'ANIMALE DOMESTICO DELLA VISITA E' NULLO
        // SE IL PROPRIETARIO DELL'ANIMALE é NULLO
        // SE L'ANIMALE HA UN VALORE IDENTIFICATIVO PERSISTITO
        // SE IL IL PROPRIETARIO DELL'ANIMALE HA UN VALORE IDENTIFICATIVO NULLO
        // IN QUESTO MODO VALIDO L'IMPLEMENTAZIONE DELLA MAPPA
        // PRIMA DI SALVARE LA VISITA
        // E' UN CODICE DIFENSIVO
        // PER ESSERE SICURI DI AVERE DEGLI
        // OGGETTI CORRETTAMENTE FORMATI
        if (visit.getPet() == null ||
                visit.getPet().getOwner() == null ||
                visit.getPet().getId() == null ||
                visit.getPet().getOwner().getId() == null){
            throw new RuntimeException("Invalid Visit");
        }

        return super.save(visit);
    }

    @Override
    public void delete(Visit object) {

        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {

        super.deleteById(id);
    }
}
