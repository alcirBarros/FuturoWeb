package br.com.selfSystem.util;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class GenericFacade<T> implements Serializable {

    private static final long serialVersionUID = -6947075831476047681L;
    private final EntityManager em;
    private final Class ec;

    public GenericFacade(EntityManager em, Class ec) {
        this.em = em;
        this.ec = ec;
    }

    public T inserir(T entity) {
        boolean commit = true;
        if (!em.getTransaction().isActive()) {
            this.beginTransaction();
        } else {
            commit = false;
        }
        em.persist(entity);
        if (commit) {
            this.commitTransaction();
        }
        return entity;
    }

    public T alterar(T entity) {
        this.beginTransaction();
        T merged = em.merge(entity);
        this.commitTransaction();
        return merged;
    }

    public void excluir(T entity) {
        this.beginTransaction();
        T merge = em.merge(entity);
        em.remove(merge);
        this.commitTransaction();
    }

    public T carregar(Object id) {
        return (T) em.find(ec, id);
    }

    public T carregar(String sigla) {
        try {
            Field field = ec.getDeclaredField("sigla");
            List<T> resultList = em.createQuery("select t from " + ec.getSimpleName() + " t where t.sigla = :sigla")
                                                                                                                                                        .setParameter("sigla", sigla)
                                                                                                                                                        .getResultList();
            if (resultList.size() > 0) {
                return resultList.get(0);
            } else {
                return null;
            }
        } catch (NoSuchFieldException ex) {
            return null;
        } catch (SecurityException ex) {
            return null;
        }
    }

    public List<T> listarTodos(String... orders) {
        String orderBy = "";
        if (orders.length > 0) {
            orderBy = " order by ";
            for (String property : orders) {
                orderBy += " t." + property + ", ";
            }
            orderBy = orderBy.substring(0, orderBy.length() - 2);
        }
        return em.createQuery("select t from " + ec.getSimpleName() + " t " + orderBy).getResultList();
    }

    public int contar() {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(ec);
        cq.select(em.getCriteriaBuilder().count(rt));
        javax.persistence.Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    public List<T> query(String query, Object... parameters) {
        Query q = em.createQuery(query);
        int i = 1;
        for (Object object : parameters) {
            q.setParameter(i, object);
            i++;
        }
        return q.getResultList();
    }

    public List<T> search(String query, String... parameters) {
        Query q = em.createQuery(query);
        int i = 1;
        for (String s : parameters) {
            q.setParameter(i, "%" + s + "%");
            i++;
        }
        return q.getResultList();
    }

    public void beginTransaction() {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
    }

    public void commitTransaction() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().commit();
        }
    }
}
