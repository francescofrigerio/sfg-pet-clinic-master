package guru.springframework.sfgpetclinic;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

// LEZIONE 183 Sostituzione di Junit4 con Junit5
// Vedere modifica al file pom.xml dei 2 moduli
/* @RunWith(SpringRunner.class) */
// Rimane un test d'integrazione qundi
// molto lento perche' viene caricato lo Spring Context
// LEZIONE 183 Sostituzione di Junit4 con Junit5
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SfgPetClinicApplicationTests {

    @Test
    public void contextLoads() {
    }

}
