package com.evatigrova.services;

import com.evatigrova.beans.User;
import com.evatigrova.dao.Dao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class UserService {

    @Autowired(required = true)
    private Dao<User> userDao;

    public static Logger log = LogManager.getLogger(UserService.class);
    public UserService() {
    }

    public User loadUserByName(String name){
        String hql = "SELECT U From com.evatigrova.beans.User U WHERE U.user_name=:name";
        Query query = userDao.getSession().createQuery(hql);
        query.setParameter("name", name);
        List<User> userList = userDao.selectByHQL(query);
        if (!userList.isEmpty()) {
            return userList.get(0);
        }
        return null;
    }
}        