package com.evatigrova.service;

import com.evatigrova.beans.*;
import com.evatigrova.dao.Dao;
import org.apache.commons.lang.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 */
@Service
@Transactional
public class PageService  implements IPageService {
    public static Logger log = LogManager.getLogger(PageService.class);

    @Autowired(required = true)
    private Dao<Page> pageDao;

    @Autowired(required = true)
    private Dao<Category> categoryDao;

    public PageService() {
    }

    /**
     * This method create Criteria for
     * showing methods
     * @param search_page_id
     * @param maxPages
     * @return
     */
    public Criteria paginationCriteria(int search_page_id, int maxPages) {

        Criteria criteria = pageDao.getSession().createCriteria(Page.class);

        int firstResult=(search_page_id-1)*maxPages;
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxPages);


        return criteria;
    }


    /**
     *
     * @param search_result
     * @param category
     * @param maxPages
     * @return
     */
    @Override
    public List<Page> getAllPages(String search_result, String category, int maxPages){
        Integer search_page_id = Integer.parseInt(search_result);
        Criteria criteria = paginationCriteria(search_page_id, maxPages);
        Validate.notEmpty(category);

        Integer category_id = Integer.parseInt(category);

        criteria.add(Restrictions.eq("category.category_id", category_id));
        return pageDao.selectByCriteria(criteria);
    }

    @Override
    public List<Page> getAllPages(String search_result, int maxPages){
        Integer search_page_id = Integer.parseInt(search_result);

        Criteria criteria = paginationCriteria(search_page_id, maxPages);
        return pageDao.selectByCriteria(criteria);
    }

    /**
     * Save page
     * @param page
     * @return
     */
    @Override
    public boolean save(Page page) {

        int id = page.getCategory().getCategory_id();
        Category category;

        category = categoryDao.load(Category.class, id);

        page.setCategory(category);
        category.getPages().add(page);

        pageDao.save(page);

        return true;
    }

    @Override
    public boolean update(Page page) {

        int id = page.getCategory().getCategory_id();

        Category category = categoryDao.load(Category.class, id);
        Page persistentPage = load(page.getPage_id());

        persistentPage.setCategory(category);
        category.getPages().add(persistentPage);

        persistentPage.getPageDetail().setPage_name(page.getPageDetail().getPage_name());
        persistentPage.getPageDetail().setDate(page.getPageDetail().getDate());

        pageDao.update(persistentPage);

        return true;
    }

    /**
     *  Delete page
     * @param page
     * @return
     */
    @Override
    public boolean deletePage(Page page) {

        Page persistentPage = pageDao.load(Page.class, page.getPage_id());
        Category category = persistentPage.getCategory();
        PageDetail pageDetail = persistentPage.getPageDetail();
        category.getPages().remove(persistentPage);
        Set<Tag> tags = persistentPage.getTags();

        for (Tag tag: tags) {
            tag.getPages().remove(page);
        }
        pageDetail.getContexts().clear();
        pageDetail.setPage(null);
        page.setPageDetail(null);

        Collection<Context> contexts = pageDetail.getContexts();
        for( Context context:contexts) {
            context.setPageDetail(null);
            contexts.remove(context);
        }

        pageDao.delete(persistentPage);

        return true;
    }

    /**
     * get page by id
     * @param id
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public Page get(long id) {
        return pageDao.get(Page.class, id);
    }

    /**
     * Load page by id
     * @param id
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public Page load(long id) {
        return pageDao.load(Page.class, id);
    }

    /**
     * Create transient object that will
     * be used as DTO to service layer
     * @return
     */
    @Override
    public Page createDtoPage(){
        Page page = new Page();
        Category category = new Category();

        PageDetail pageDetail = new PageDetail();
        Set<Page> pages = new HashSet<Page>();

        page.setCategory(category);
        category.setPages(pages);
        category.getPages().add(page);

        page.setPageDetail(pageDetail);
        pageDetail.setPage(page);

        return page;
    }


}