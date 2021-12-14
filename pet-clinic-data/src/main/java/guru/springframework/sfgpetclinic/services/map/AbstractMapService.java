package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.BaseEntity;

import java.util.*;

/**
 * Created by jt on 7/21/18.
 * LEZIONE 125
 * LEZIONE 150
 * SERVIZIO DI MAPPE CIOE' UN IMPLEMENTAZIONE
 * CHE FORNISCE UN HASMAP E ALLO STESSO
 * UN IMPLEMENTAZIONE CHE FORNISCE SPRING DATA JPA
 * COLLEGANDO LE DUE COSE A LIVELLO DI SERVIZIO
 * CON L'OBIETTIVO  DI ASTRARRE IL PIU' POSSIBILE
 * L'IMPLEMENTAZIONE DEL SOTTOSTANTE ARCHIVIO PERSISTENTE
 * QUINDI LO CONFRONTIAMO PER USARE UN
 * IMPLEMENTAZIONE DI HASHMAP PER MANTENERE I DATI
 * DI UN DATABASE SQL
 * ATTRAVERSO IL DB SQL USEREMO H2 HIBERNATE
 * PER L'IMPLEMENTAZIONE ORM (OBJECT RELATIONAL MAPPING)
 * E POI AL DI SOPRA DI QUESTO USEREMO SPRING DATA JPA
 * QUINDI SI VUOLE
 * 1- ASTRARRE IL PIU' POSSIBILE
 * 2- USARE SPRING PER CONTROLLARE QUALE DI QUESTI
 * 3- CABLARE IN FASE DI RUNTIME
 * 4- AVERE UN PROFILE CHE SI PUO' ESEGUIRE COME PROFILO HASHMAP
 * CON LA NOSTRA APPLICAZIONE WEB
 * 5- AVERE UN ALTRO PROFILO IN CUI POSSIAMO USARE UN DB SQL
 * IN MODO DA POTER PASSARE AVANTI E INDIETRO CON UN INTERRUTTORE
 *
 * PER ARRIVARE AD AVERE I DUE SERVIZI DOBBIAMO INIZIARE
 * IMPLEMENTANDO IL SERVIZIO
 */
public abstract class AbstractMapService<T extends BaseEntity, ID extends Long> {

    protected Map<Long, T> map = new HashMap<>();

    Set<T> findAll(){

        return new HashSet<>(map.values());
    }

    T findById(ID id) {
        return map.get(id);
    }

    T save(T object){

        if(object != null) {
            if(object.getId() == null){
                object.setId(getNextId());
            }

            map.put(object.getId(), object);
        } else {
            throw new RuntimeException("Object cannot be null");
        }

        return object;
    }

    void deleteById(ID id){

        map.remove(id);
    }

    void delete(T object){

        map.entrySet().removeIf(entry -> entry.getValue().equals(object));
    }

    private Long getNextId(){

        Long nextId = null;

        try {
            nextId = Collections.max(map.keySet()) + 1;
        } catch (NoSuchElementException e) {
            nextId = 1L;
        }

        return nextId;
    }
}
