package com.evatigrova.dao;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 *
 */
@Repository("baseDao")
public class BaseDao<T> implements Dao<T> {
    public static Logger log = LogManager.getLogger(BaseDao.class);


    protected SessionFactory sessionFactory;

    public BaseDao() {
    }

    @Autowired(required = true)
    public BaseDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getSession() {
        if (sessionFactory!=null){
            return sessionFactory.getCurrentSession();
        }
        return sessionFactory.openSession();
    }

    @Override
    public void saveOrUpdate(T t) {
        getSession().saveOrUpdate(t);
        log.info("saveOrUpdate:" + t );
    }

    @Override
    public void save(T t) {
        getSession().save(t);
        log.info("save:" + t );
    }

    @Override
    public void update(T t) {
        getSession().update(t);
        getSession().flush();
        log.info("update:" + t );
    }

    @Override
    public T load(Class<T> clazz, Serializable id) {
        T t = (T) getSession().load(clazz, id);
        log.info("load:" + id);
        return t;
    }

    @Override
    public T get(Class<T> clazz, Serializable id) {
        T t = (T) getSession().get(clazz, id);
        log.info("load:" + id);
        return t;
    }

    @Override
    public void delete(T t) {
        getSession().delete(t);
        log.info("delete:" + t);
    }

    @Override
    public void deleteById(Class<T> clazz, Serializable id){
        T t = load(clazz, id);
        if (t != null) {
            delete(t);
        }
    }

    @Override
    public void refresh(T t) {
        log.info("Refresh:" + t);
        getSession().refresh(t);
    }

    @Override
    public List<T> selectByCriteria(Criteria criteria){

        List<T> list = (List<T>) criteria.list();
        log.info("select by criteria done");

        return list;
    }

    @Override
    public List<T> selectByHQL(Query query){
        return (List<T>) query.list();
    }

    @Override
    public Long selectUniqueByHQL(Query query){
        return (Long) query.uniqueResult();
    }



    @Override
    public List<T> selectAll() {
        List<T> list = sessionFactory.getCurrentSession().createQuery("from " + this.getEntityClass().getSimpleName()).list();
        return list;
    }

    @Override
    public T merge(T t) {
        return (T) getSession().merge(t);
    }

    private Class<?> getEntityClass() {
        final Class<?> result = GenericTypeResolver.resolveTypeArguments(getClass(), BaseDao.class)[0];
        if (result == null) {
            throw new IllegalArgumentException("can not resolve type argument for DAO: " + getClass().getSimpleName());
        }
        return result;
    }


}
