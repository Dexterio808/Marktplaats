package nl.belastingdienst.product.Product;

import nl.belastingdienst.abstractbp.ControllerBP;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class ProductController implements ControllerBP<Product> {

    @Inject
    private ProductDao productDao;

    @Override
    public List<Product> findAll() {
        return productDao.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(productDao.findByID(id));
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
}
