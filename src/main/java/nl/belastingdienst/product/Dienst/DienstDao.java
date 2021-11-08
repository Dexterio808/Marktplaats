package nl.belastingdienst.product.Dienst;

import nl.belastingdienst.abstractbp.DaoBP;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Singleton
public class DienstDao implements DaoBP<Dienst> {
    @Inject
    private EntityManager em;


    @Override
    public Dienst findByID(Long id) {
        return em.find(Dienst.class, id);
    }

    @Override
    public List<Dienst> findAll() {
        TypedQuery<Dienst> query = em.createQuery("SELECT d FROM Dienst d", Dienst.class);
        return query.getResultList();
    }

    @Override
    public void save(Dienst dienst) {
        performTransaction(()->em.persist(dienst));
    }

    @Override
    public void update(Dienst dienst) {
        performTransaction(()->em.merge(dienst));
    }

    @Override
    public void delete(Dienst dienst) {
        performTransaction(()->em.merge(dienst));
    }

    public void performTransaction(Runnable runnable){
        em.getTransaction().begin();
        runnable.run();
        em.getTransaction().commit();
    }

    public List<Dienst> findAllActiveDienst(long gebruikerId) {
        TypedQuery<Dienst> query = em.createQuery("SELECT d FROM Dienst d WHERE d.gebruiker.id = :gebruikerId AND d.verkocht = false", Dienst.class)
                .setParameter("gebruikerId", gebruikerId);
        return query.getResultList();
    }

    public List<Dienst> findAllSoldDienst(long gebruikerId) {
        TypedQuery<Dienst> query = em.createQuery("SELECT d FROM Dienst d WHERE d.gebruiker.id = :gebruikerId AND d.verkocht = true", Dienst.class)
                .setParameter("gebruikerId", gebruikerId);
        return query.getResultList();
    }
}
