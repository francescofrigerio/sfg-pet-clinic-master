package guru.springframework.sfgpetclinic.services;

import guru.springframework.sfgpetclinic.model.Owner;

import java.util.List;


/**
 * Created by jt on 7/18/18.
 * LEZIONE 151
 */
public interface OwnerService extends CrudService<Owner, Long> {

    // LEZIONE 151
    // AGGIUNTI RISPETTO ALLA INTERFACCIA DA CUI EREDITA
    // DOVRANNO ESSERE IMPLEMENTATI INSIEME
    // AI METODI DELL'INTERFACCIA CRUD
    // NELLE CLASSE CHE IMPLEMENTANO OwnerService
    // I METODI SONO GLI STESSI DELL'INTERFACCIA
    // REPOSITORY RELATIVA AI PROPRIETARI
    Owner findByLastName(String lastName);
    // LEZIONE 151

    List<Owner> findAllByLastNameLike(String lastName);
 }
