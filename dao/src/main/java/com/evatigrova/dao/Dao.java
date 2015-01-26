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

    T load(Class<T> clazz, Serializable id) ;

    public List<T> selectByCriteria(Criteria criteria);

    public List<T> selectByHQL(Query query);

    public List<T> selectAll();

    public void refresh(T t);




    void delete(T t);

    public void deleteById(Class<T> clazz, Serializable id);


}
