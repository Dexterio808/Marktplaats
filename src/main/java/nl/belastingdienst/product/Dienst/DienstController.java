package nl.belastingdienst.product.Dienst;

import nl.belastingdienst.abstractbp.ControllerBP;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class DienstController implements ControllerBP<Dienst> {

    @Inject
    private DienstDao dienstDao;

    @Override
    public List<Dienst> findAll() {
        return dienstDao.findAll();
    }

    @Override
    public Dienst findById(Long id) {
        return dienstDao.findByID(id);
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

    public List<Dienst> findAllActiveDienst(long id) {
        return dienstDao.findAllActiveDienst(id);
    }

    public List<Dienst> findAllSoldDienst(long id){
        return dienstDao.findAllSoldDienst(id);
    }

}
