package nl.belastingdienst.categorie.Dienst;

import nl.belastingdienst.abstractbp.DaoBP;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class DienstCategorieDao implements DaoBP<DienstCategorie> {

    @Inject
    private EntityManager em;

    @Override
    public DienstCategorie findByID(Long id) {
        return em.find(DienstCategorie.class, id);
    }

    @Override
    public List<DienstCategorie> findAll() {
        TypedQuery<DienstCategorie> query = em.createQuery("SELECT d FROM DienstCategorie d", DienstCategorie.class);
        return query.getResultList();
    }

    @Override
    public void save(DienstCategorie dienstCategorie) {
        performTransaction(()->em.persist(dienstCategorie));
    }

    @Override
    public void update(DienstCategorie dienstCategorie) {
        performTransaction(()->em.merge(dienstCategorie));
    }

    @Override
    public void delete(DienstCategorie dienstCategorie) {
        performTransaction(()->em.merge(dienstCategorie));
    }


    public void performTransaction(Runnable runnable){
        em.getTransaction().begin();
        runnable.run();
        em.getTransaction().commit();
    }
}
