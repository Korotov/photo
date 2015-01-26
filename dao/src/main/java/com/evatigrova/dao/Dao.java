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

    void saveOrUpdate(T t);

    public void save(T t);

    public void update(T t);

    T load(Class<T> clazz, Serializable id) ;

    public List<T> selectByCriteria(Criteria criteria);

    public List<T> selectByHQL(Query query);

    public List<T> selectAll();

    public void refresh(T t);

    public T merge(T t);




    void delete(T t);

    public void deleteById(Class<T> clazz, Serializable id);


}
