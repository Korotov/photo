package com.evatigrova;

import com.evatigrova.beans.Category;
import com.evatigrova.beans.Context;
import com.evatigrova.beans.Page;
import com.evatigrova.beans.PageDetail;
import com.evatigrova.service.ICategoryService;
import com.evatigrova.service.IPageService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 *
 */

public class TestService {
    private ApplicationContext applicationContext= new ClassPathXmlApplicationContext("service.xml");

    @Test
    public void testPageService() {

        IPageService pageService = (IPageService) applicationContext.getBean("pageService");
        ICategoryService categoryService = (ICategoryService) applicationContext.getBean("categoryService");
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
