package core.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

/**
 * Created by saeed on 1/March/15 AD.
 */
public abstract class GenericDaoImpl<T, ID extends Serializable> implements GenericDao<T, ID> {

    @PersistenceContext
    private EntityManager entityManager ;

    protected abstract Class<T> getEntityClass();

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public void persist(T entity) {
        entityManager.persist(entity);
    }

    @Override
    public List<T> getAll() {
        return entityManager.createQuery("from " + getEntityClass().getName()).getResultList();
    }

    @Override
    public Long count() {
        return (Long) entityManager.createQuery("select count(*) from " 
                + getEntityClass().getName()).getSingleResult();
    }

    @Override
    public T find(ID id) {
        return  (T) entityManager.find(getEntityClass(), id);
    }

    @Override
    public T merge(T entity){
        return entityManager.merge(entity);
    }

    @Override
    public void remove(ID id){
        entityManager.remove(find(id));
    }

}