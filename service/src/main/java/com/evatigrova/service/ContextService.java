package com.evatigrova.service;

import com.evatigrova.beans.Context;
import com.evatigrova.dao.Dao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 */
@Service
@Transactional
public class ContextService implements IContextService {
    public static Logger log = LogManager.getLogger(ContextService.class);

    @Autowired(required = true)
    private Dao<Context> contextDao;

    public ContextService() {
    }

    /**
     * Method realize Dao HQL select
     * to get max index of page contexts
     * @param page
     * @return
     */
//    public int getMaxContextIndex(Page page) {
//            int maxIndex = contextDao.getMaxContextIndex(page);
//            return maxIndex;
//    }
}        