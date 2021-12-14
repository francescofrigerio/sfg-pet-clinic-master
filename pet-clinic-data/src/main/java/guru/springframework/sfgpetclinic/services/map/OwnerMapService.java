package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.repositories.OwnerRepository;
import guru.springframework.sfgpetclinic.repositories.PetRepository;
import guru.springframework.sfgpetclinic.repositories.PetTypeRepository;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Created by jt on 7/21/18.
 *
 */
@Service
// LEZIONE 159 SE COMMENTO QUI E IN OwnerSDJpaService
// posso avere errori runtime
// Se non specifico un profilo attivo
// sara' attivo il profilo predefinito
// quindi voglio implementare questa classe
// solo con il profilo predefinito oppure con il profilo map
// Se si specifica un profilo attivo
// come springdatajpa questa classe non sara
// implementata nel context di Spring
// Pertanto tramite la specificia
// del profile e se non specifichiamo
// è questo il default
// I due servizi sono alternativi
// perchè entrambi implementano la stessa interfaccia OwnerService
// che viene poi utilizzata nella classe
// DataLoader del pkg bootstrao dell'applicazione web
@Profile({"default", "map"})
public class OwnerMapService extends AbstractMapService<Owner, Long> implements OwnerService {

    // LEZIONE 124
    private final PetTypeService petTypeService;
    private final PetService petService;

    public OwnerMapService(PetTypeService petTypeService,
                           PetService petService) {

        this.petTypeService = petTypeService;
        this.petService = petService;
    }


    // LEZIONE 124
    @Override
    public Set<Owner> findAll() {

        return super.findAll();

    }

    @Override
    public Owner findById(Long id) {

        return super.findById(id);

    }

    @Override
    // LEZIONE 124
    // Stiamo lavorando con hashmap
    // di proprietari animali e tipi di animali
    // Tutti gli oggetti stanno mantenendo i loro ID
    // e si cerca nel codice di mantenere
    // la sincronizzazione degli ID all'interno
    // di tutti gli oggetti
    public Owner save(Owner object) {

            System.out.println("START SAVE ON MAP SERVICE");
            Owner saveOwner = null;

            if (object != null)
            {

                if (object.getPets() != null)
                {
                    // Ciclo sul vettore degli animnali domestico
                    // Per ogni animale domestico
                    // Partire con il seguente e all'interno
                    // digitare il codice del ciclo
                /* object.getPets().forEach(pet -> {

                        });
                */
                    object.getPets().forEach(pet -> {
                                if (pet.getPetType() != null) {
                                    // SE IL CAMPO ID DEL PETTYPE E' NULLO
                                    // DEVO SALVARE IL TIPO DI ANIMALE
                                    // PERCHE' SI TRATTA DI UNA INSERT
                                    if (pet.getPetType().getId() == null) {
                                        // Per consolidare l'animale
                                        // devo consolidare anche il suo tipo
                                        // cosi prendiamo il valore di ID
                                        // dall'oggetto MapTypeService
                                        pet.setPetType(petTypeService.save(pet.getPetType()));
                                    }
                                } else {
                                    throw new RuntimeException("Pet Type is required");
                                }

                                // Controllo sempre il valore ID
                                // che potrebbe essere nullo
                                // perche' un valore legato
                                // alla persistenza e al consolidamento
                                // degli oggetti gestito dalle lib
                                // Ottengo l'oggetto animale domestico
                                // salvato e imposto il valore id sull'oggetti
                                // per del vettore di animali domestici
                                // del proprietario . Il tutto in modo
                                // esplicito per sicurezza anche se non e'
                                // obbligatorio farlo
                                // E' meglio prendersi cura degli ID
                                // degli oggetti
                                if (pet.getId() == null) {
                                    Pet savedPet = petService.save(pet);
                                    pet.setId(savedPet.getId());
                                }
                            }
                    );
                }
                // alla fine posso salvare il mio oggetto
                // proprietario
                return super.save(object);

            } else {
                // Se si prova a salvare un oggetto null
                // ritorna null
                return null;
            }


    }
    /* public Owner save(Owner object) {

        if(object != null){
            if (object.getPets() != null) {
                object.getPets().forEach(pet -> {
                    if (pet.getPetType() != null){
                        if(pet.getPetType().getId() == null){
                            pet.setPetType(petTypeService.save(pet.getPetType()));
                        }
                    } else {
                        throw new RuntimeException("Pet Type is required");
                    }

                    if(pet.getId() == null){
                        Pet savedPet = petService.save(pet);
                        pet.setId(savedPet.getId());
                    }
                });
            }

            return super.save(object);

        } else {
            return null;
        }
    }
    */
    // LEZIONE 124

    @Override
    public void delete(Owner object) {

            super.delete(object);
    }

    @Override
    public void deleteById(Long id) {

            super.deleteById(id);
    }

    @Override
    public Owner findByLastName(String lastName) {
        return this.findAll()
                .stream()
                .filter(owner -> owner.getLastName().equalsIgnoreCase(lastName))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Owner> findAllByLastNameLike(String lastName) {

        //todo - impl
        return null;
    }
}
