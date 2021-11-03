package nl.belastingdienst.gebruiker;

import nl.belastingdienst.abstractbp.ServiceBluePrint;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Singleton
public class GebruikerService implements ServiceBluePrint<Gebruiker> {

    @Inject
    private EntityManager em;

    @Override
    public Gebruiker find(Long id) {
        return em.find(Gebruiker.class, id);
    }

    public Gebruiker findByEmail(String email){
        var query = em.createQuery("SELECT g FROM Gebruiker g WHERE g.email = :email", Gebruiker.class)
                .setParameter("email", email);
        return (Gebruiker) query.getSingleResult();
    }

    @Override
    public List<Gebruiker> findAll() {
        TypedQuery<Gebruiker> query = em.createQuery("SELECT g from Gebruiker g", Gebruiker.class);
        return query.getResultList();
    }

    @Override
    public void save(Gebruiker gebruiker) {
        performTransaction(()->em.persist(gebruiker));
    }

    @Override
    public void update(Gebruiker gebruiker) {
        performTransaction(()->em.merge(gebruiker));
    }

    @Override
    public void delete(Gebruiker gebruiker) {
        performTransaction(()->em.remove(gebruiker));
    }

    public void performTransaction(Runnable runnable){
        em.getTransaction().begin();
        runnable.run();
        em.getTransaction().commit();
    }
}
