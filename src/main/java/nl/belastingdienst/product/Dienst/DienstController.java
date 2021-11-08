package nl.belastingdienst.product.Dienst;

import nl.belastingdienst.abstractbp.ControllerBP;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class DienstController implements ControllerBP<Dienst> {

    @Inject
    DienstDao dienstDao;

    @Override
    public List<Dienst> findAll() {
        return dienstDao.findAll();
    }

    @Override
    public Optional<Dienst> findById(Long id) {
        return Optional.ofNullable(dienstDao.findByID(id));
    }

    @Override
    public void save(Dienst dienst) {
        dienstDao.save(dienst);
    }

    @Override
    public void update(Dienst dienst) {
        dienstDao.update(dienst);
    }

    @Override
    public void delete(Dienst dienst) {
        dienstDao.delete(dienst);
    }
}
