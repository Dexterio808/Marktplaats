package nl.belastingdienst.categorie.Product;

import nl.belastingdienst.abstractbp.ControllerBP;

import javax.inject.Inject;
import java.util.List;

public class ProductCategorieController implements ControllerBP<ProductCategorie> {

    @Inject ProductCategorieDao productCategorieDao;

    @Override
    public List<ProductCategorie> findAll() {
        return productCategorieDao.findAll();
    }

    @Override
    public ProductCategorie findById(Long id) {
        return productCategorieDao.findByID(id);
    }

    @Override
    public void save(ProductCategorie productCategorie) {
        productCategorieDao.save(productCategorie);
    }

    @Override
    public void update(ProductCategorie productCategorie) {
        productCategorieDao.save(productCategorie);
    }

    @Override
    public void delete(ProductCategorie productCategorie) {
        productCategorieDao.delete(productCategorie);
    }
}
