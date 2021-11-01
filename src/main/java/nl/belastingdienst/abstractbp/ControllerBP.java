package nl.belastingdienst.abstractbp;

import java.util.List;
import java.util.Optional;

public interface ControllerBP<T>{

    public List<T> findAll();

    public Optional<T> findById( Long id);

    public void save(T t);

    public void update(T t);

    public void delete(T t);

}
