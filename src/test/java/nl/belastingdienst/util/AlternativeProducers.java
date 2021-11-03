package nl.belastingdienst.util;

import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.Scanner;

@Alternative
public class AlternativeProducers {

    @Produces
    public static EntityManager em() {
        return Persistence.createEntityManagerFactory("H2").createEntityManager();
    }
}
