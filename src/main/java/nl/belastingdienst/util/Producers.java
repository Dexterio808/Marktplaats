package nl.belastingdienst.util;

import javax.enterprise.inject.Produces;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

@Singleton
public class Producers {

    @Produces
    public static EntityManager em() {
        return Persistence.createEntityManagerFactory("MySQL").createEntityManager();
    }

}
