package nl.belastingdienst.product.Product;

import nl.belastingdienst.abstractbp.DaoBP;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Singleton
public class ProductDao implements DaoBP<Product> {
    @Inject
    private EntityManager em;

    @Override
    public Product findByID(Long id) {
        return em.find(Product.class, id);
    }

    @Override
    public List<Product> findAll() {
        TypedQuery<Product> query = em.createQuery("SELECT p FROM Product g", Product.class);
        return query.getResultList();
    }

    @Override
    public void save(Product product) {
        performTransaction(()->em.persist(product));
    }

    @Override
    public void update(Product product) {
        performTransaction(()->em.merge(product));
    }

    @Override
    public void delete(Product product) {
        performTransaction(()->em.merge(product));
    }

    public void performTransaction(Runnable runnable){
        em.getTransaction().begin();
        runnable.run();
        em.getTransaction().commit();
    }
}
