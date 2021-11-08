package nl.belastingdienst.product.Product;

import nl.belastingdienst.abstractbp.ControllerBP;

import javax.inject.Inject;
import java.util.List;

public class ProductController implements ControllerBP<Product> {

    @Inject
    private ProductDao productDao;

    @Override
    public List<Product> findAll() {
        return productDao.findAll();
    }

    @Override
    public Product findById(Long id) {
        return productDao.findByID(id);
    }

    @Override
    public void save(Product product) {
        productDao.save(product);
    }

    @Override
    public void update(Product product) {
        productDao.update(product);
    }

    @Override
    public void delete(Product product) {
        productDao.delete(product);
    }

    public List<Product> findAllActiveProduct(Long gebruikerID) {
        return productDao.findAllActiveProduct(gebruikerID);
    }

    public List<Product> findAllSoldProduct(Long gebruikerID) {
        return productDao.findAllSoldProduct(gebruikerID);
    }
}
