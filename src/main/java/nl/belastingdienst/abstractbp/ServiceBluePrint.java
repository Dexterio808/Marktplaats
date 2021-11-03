package nl.belastingdienst.abstractbp;

import java.util.List;

public interface ServiceBluePrint <T>{
    T find(Long id);
    List<T> findAll();
    void save(T t);
    void update(T t);
    void delete(T t);
}
