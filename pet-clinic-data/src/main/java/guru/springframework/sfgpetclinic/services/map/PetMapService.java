package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.services.PetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by jt on 7/21/18.
 *
 */
@Service
// LEZIONE 154
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
public class PetMapService extends AbstractMapService<Pet, Long> implements PetService {
    @Override
    public Set<Pet> findAll() {
        return super.findAll();
    }

    @Override
    public Pet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Pet save(Pet object) {
        return super.save(object);
    }

    @Override
    public void delete(Pet object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
