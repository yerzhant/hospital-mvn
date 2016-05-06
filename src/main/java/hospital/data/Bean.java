/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.data;

import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 *
 * @author yerzhan
 * @param <T>
 */
public abstract class Bean<T> {

    @Inject
    protected EntityManager em;

    private final Class<T> entityClass;

    public Bean(Class<T> c) {
        entityClass = c;
    }

    public void create(T entity) {
        em.persist(entity);
    }

    public void edit(T entity) {
        em.merge(entity);
    }

    public void delete(T entity) {
        em.remove(em.merge(entity));
    }

    public void refresh() {
        em.getEntityManagerFactory().getCache().evict(entityClass);
    }

    public T find(Object id) {
        return em.find(entityClass, id);
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return em.createQuery(cq).getResultList();
    }
}
