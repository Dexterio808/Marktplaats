package nl.belastingdienst.product.Product;

import nl.belastingdienst.abstractbp.DaoBP;
import nl.belastingdienst.gebruiker.GebruikerController;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Singleton
public class ProductDao implements DaoBP<Product> {
    @Inject
    private EntityManager em;
    @Inject
    private GebruikerController gebruikerController;

    @Override
    public Product findByID(Long id) {
        Product product = em.find(Product.class, id);
        if (product != null) em.detach(product);
        return product;
    }

    @Override
    public List<Product> findAll() {
        TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p", Product.class);
        return query.getResultList();
    }

    public List<Product> findAllActiveProduct(Long gebruikerId) {
        TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p WHERE p.gebruiker.id = :gebruikerId AND p.verkocht = false", Product.class)
                .setParameter("gebruikerId", gebruikerId);
        return query.getResultList();
    }

    public List<Product> findAllSoldProduct(Long gebruikerId) {
        TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p WHERE p.gebruiker.id = :gebruikerId AND p.verkocht = true", Product.class)
                .setParameter("gebruikerId", gebruikerId);
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
