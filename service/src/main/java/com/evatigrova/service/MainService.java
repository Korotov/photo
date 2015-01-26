package com.evatigrova.service;

import com.evatigrova.beans.Category;
import com.evatigrova.beans.Context;
import com.evatigrova.beans.Page;
import com.evatigrova.beans.PageDetail;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 *
 */
public class MainService {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("service.xml");
        SessionFactory sessionFactory = (SessionFactory) applicationContext.getBean("sessionFactory");

        PageService pageService  = (PageService) applicationContext.getBean("pageService");
        CategoryService categoryService  = (CategoryService) applicationContext.getBean("categoryService");

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

        categoryService.save(category);
        pageService.save(page);


    }
}
