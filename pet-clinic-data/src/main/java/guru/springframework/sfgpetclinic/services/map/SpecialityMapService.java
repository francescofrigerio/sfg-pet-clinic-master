package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.services.SpecialtyService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by jt on 7/31/18.
 * LEZIONE 125
 * estende dalla classe base in modo da lavorare con una hashMap
 */
// Contrassegnato come servizio in modo
// che e' trattato come componente Bean
    // e viene scansionato e incluso nel context di Spring
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
public class SpecialityMapService extends AbstractMapService<Speciality, Long> implements SpecialtyService {

    @Override
    public Set<Speciality> findAll() {

        return super.findAll();
    }

    @Override
    public Speciality findById(Long id) {

        return super.findById(id);
    }

    @Override
    public Speciality save(Speciality object) {

        return super.save(object);
    }

    @Override
    public void delete(Speciality object) {

        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {

        super.deleteById(id);
    }
    // LEZIONE 125
}
