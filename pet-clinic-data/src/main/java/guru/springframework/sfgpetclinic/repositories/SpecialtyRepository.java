package guru.springframework.sfgpetclinic.repositories;

import guru.springframework.sfgpetclinic.model.Speciality;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by jt on 8/5/18.
 * LEZIONE 150
 * Estende Interfaccia CrudRepository
 * specificando il Tipo generico Di Oggetto T come Speciality e
 * il tipo generico di ID value dell' entita come Long
 * questo perche' il tipo del campo ID dell'oggetto
 * BaseEntity da cui ereditano Owner e Person è un Long
 * I due tipi di dati devono essere coerenti tra di loro.
 * ha delle alternative avanzate
 * come org.springframework.data.repository.PagingAndSortingRepository;
 *  come org.springframework.data.jpa.repository.JpaRepository;
 */
public interface SpecialtyRepository extends CrudRepository<Speciality, Long> {
}
