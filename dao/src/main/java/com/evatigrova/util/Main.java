package com.evatigrova.util;

import com.evatigrova.beans.Category;
import com.evatigrova.beans.Context;
import com.evatigrova.beans.Page;
import com.evatigrova.beans.PageDetail;
import com.evatigrova.dao.Dao;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 *
 */
public class Main {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("dao-beans.xml");
        SessionFactory sessionFactory = (SessionFactory) applicationContext.getBean("sessionFactory");

        Dao pageDao = (Dao<Page>) applicationContext.getBean("baseDao");

        Context context = new Context();

        Collection<Context> contexts = new HashSet<Context>();
        contexts.add(context);

        PageDetail pageDetail = new PageDetail();
        pageDetail.setContexts(contexts);
        context.setPageDetail(pageDetail);

        Page page = new Page();
        page.setPageDetail(pageDetail);
        pageDetail.setPage(page);

        Set<Page> pages = new HashSet<Page>();
        pages.add(page);
        Category category = new Category();
        category.setPages(pages);
        page.setCategory(category);

        pageDao.saveOrUpdate(page);
        pageDao.getSession().flush();

    }
}
