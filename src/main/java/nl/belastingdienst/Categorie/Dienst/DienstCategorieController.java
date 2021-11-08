package nl.belastingdienst.Categorie.Dienst;

import nl.belastingdienst.abstractbp.ControllerBP;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class DienstCategorieController implements ControllerBP<DienstCategorie> {

    @Inject
    private DienstCategorieDao dienstCategorieDao;

    @Override
    public List<DienstCategorie> findAll() {
        return dienstCategorieDao.findAll();
    }

    @Override
    public Optional<DienstCategorie> findById(Long id) {
        return Optional.ofNullable(dienstCategorieDao.findByID(id));
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
