package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.SpecialtyService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by jt on 7/21/18.
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
public class VetMapService extends AbstractMapService<Vet, Long> implements VetService {

    private final SpecialtyService specialtyService;

    public VetMapService(SpecialtyService specialtyService) {

        this.specialtyService = specialtyService;
    }

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    // LEZIONE 125
    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Vet save(Vet object) {

        // LEZIONE 126
        // PER GARANTIRE IL SINCRONISMO
        // DEI VALORI SUI CAMPI CHIAVE ID
        // VEDERE I COMMENTI SU OWNERMAPSERVICES
        // PER OGNI SPECIALITA DEL VETERINARIO
        // CONTROLLO SIA PRESENTI IL VALORE
        // DEL CAMPO ID E IN CASO DI ASSENNZA
        // CONSOLIDO L'OGGETTO CON IL METODO
        // SAVE E POI RICAVO IL VALORE DEL CAMPO ID
        // PRATICAMENTE AGGIUNGO UN PEZZO DI CODICE
        // DIFENSIVO PERCHE' POSSIAMO AVERE
        // UN OGGETTO VETERINARIO CON DELLE SPECIALITA'
        // CHE NON SONO PERSISTITE NON AVENDO UN VALORE
        // DI ID DEFINITO
        // E' UNA SPECIE DI IMITAZIONE DELLE COSE
        // CHE HIBERNATE FA PER NOI
        if (object.getSpecialities().size() > 0){
            object.getSpecialities().forEach(speciality -> {
                if(speciality.getId() == null){
                    Speciality savedSpecialty = specialtyService.save(speciality);
                    speciality.setId(savedSpecialty.getId());
                }
            });
        }

        return super.save(object);
    }

    @Override
    public void delete(Vet object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
