package nl.belastingdienst.categorie.Product;

import nl.belastingdienst.abstractbp.DaoBP;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Singleton
public class ProductCategorieDao implements DaoBP<ProductCategorie> {

    @Inject
    private EntityManager em;

    @Override
    public ProductCategorie findByID(Long id) {
        return em.find(ProductCategorie.class, id);
    }

    @Override
    public List<ProductCategorie> findAll() {
        TypedQuery<ProductCategorie> query = em.createQuery("SELECT p FROM ProductCategorie p", ProductCategorie.class);
        return query.getResultList();
    }

    @Override
    public void save(ProductCategorie productCategorie) {
        performTransaction(()->em.persist(productCategorie));
    }

    @Override
    public void update(ProductCategorie productCategorie) {
        performTransaction(()->em.merge(productCategorie));
    }

    @Override
    public void delete(ProductCategorie productCategorie) {
        performTransaction(()->em.merge(productCategorie));
    }

    public void performTransaction(Runnable runnable){
        em.getTransaction().begin();
        runnable.run();
        em.getTransaction().commit();
    }
}
