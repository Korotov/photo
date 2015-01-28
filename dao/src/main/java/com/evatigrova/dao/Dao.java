package com.evatigrova.dao;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;

/**
 * generic interface for all dao classes
 */
public interface Dao<T> {
    Session getSession();

    //** create update

    void saveOrUpdate(T t);

    public void save(T t);

    public void update(T t);

    //** read

    T load(Class<T> clazz, Serializable id) ;

    public T get(Class<T> clazz, Serializable id);

    public List<T> selectByCriteria(Criteria criteria);

    public List<T> selectByHQL(Query query);

    public List<T> selectAll();

    public T merge(T t);

    public void refresh(T t);

    //** delete

    void delete(T t);

    public void deleteById(Class<T> clazz, Serializable id);


}
