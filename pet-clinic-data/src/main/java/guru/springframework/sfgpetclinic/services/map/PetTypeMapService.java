package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by jt on 7/29/18.
 * // LEZIONE 122
 */
// LEZIONE 122 in modo che la classe sia identificata
// come componente di Spring
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
public class PetTypeMapService extends AbstractMapService<PetType, Long> implements PetTypeService {

    @Override
    // LEZIONE 122
    public Set<PetType> findAll() {
        return super.findAll();
    }

    @Override
    // LEZIONE 122
    public PetType findById(Long id) {
        return super.findById(id);
    }

    @Override
    // LEZIONE 122
    public PetType save(PetType object) {
        return super.save(object);
    }

    @Override
    // LEZIONE 122
    public void delete(PetType object) {
        super.delete(object);
    }

    @Override
    // LEZIONE 122
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
