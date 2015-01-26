package com.evatigrova.dao;

import com.evatigrova.beans.Category;
import com.evatigrova.beans.Context;
import com.evatigrova.beans.Page;
import com.evatigrova.beans.PageDetail;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 *
 */
@Ignore
public class TestDao {
    private ApplicationContext applicationContext;


    @BeforeClass
    public static void init() {
    }

    @Test
    public void testDao() {
        applicationContext = new ClassPathXmlApplicationContext("dao-beans.xml");
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
