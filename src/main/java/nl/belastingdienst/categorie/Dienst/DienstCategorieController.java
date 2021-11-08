package nl.belastingdienst.categorie.Dienst;

import nl.belastingdienst.abstractbp.ControllerBP;

import javax.inject.Inject;
import java.util.List;

public class DienstCategorieController implements ControllerBP<DienstCategorie> {

    @Inject
    private DienstCategorieDao dienstCategorieDao;

    @Override
    public List<DienstCategorie> findAll() {
        return dienstCategorieDao.findAll();
    }

    @Override
    public DienstCategorie findById(Long id) {
        return dienstCategorieDao.findByID(id);
    }

    @Override
    public void save(DienstCategorie dienstCategorie) {
        dienstCategorieDao.save(dienstCategorie);
    }

    @Override
    public void update(DienstCategorie dienstCategorie) {
        dienstCategorieDao.update(dienstCategorie);
    }

    @Override
    public void delete(DienstCategorie dienstCategorie) {
        dienstCategorieDao.delete(dienstCategorie);
    }
}
