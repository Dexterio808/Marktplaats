package nl.belastingdienst.util;

import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.Scanner;

import static org.mockito.Mockito.mock;

@Alternative
public class AlternativeProducers {

/*    @Produces
    public static Scanner mockScanner(){
        return mock(Scanner.class);
    }*/

    @Produces
    public static EntityManager em() {
        return Persistence.createEntityManagerFactory("H2").createEntityManager();
    }
}
