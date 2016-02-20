package core.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by saeed on 1/March/15 AD.
 */
public interface GenericDao<T, ID extends Serializable>{
    T persist(T entity);
    List<T> getAll();
    Long count();
    T find(ID id);
    T merge(T entity);
    void remove(ID id);
}
