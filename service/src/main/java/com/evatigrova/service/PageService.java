package com.evatigrova.service;

import com.evatigrova.beans.*;
import com.evatigrova.dao.Dao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
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



    public List<Page> getPagesByCategory(int category_id) {
        String selectPagesByCategory = "FROM com.evatigrova.beans.Page P WHERE P.category.category_id=:id ORDER BY P.date DESC";
        Query query = pageDao.getSession().createQuery(selectPagesByCategory);
        query.setParameter("id", category_id);
        return pageDao.selectByHQL(query);
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
     * @param search_page_id
     * @param maxPages
     * @return
     */
    @Override
    public List<Page> getAllPages(int search_page_id, int maxPages){
        Criteria criteria = paginationCriteria(search_page_id, maxPages);
        return pageDao.selectByCriteria(criteria);
    }

    /**
     * select and return pages for first page list
     * @param maxPages
     * @return
     */
    @Override
    public List<Page> getAllPages(int maxPages) {
        Criteria criteria = paginationCriteria(0, maxPages);
        return pageDao.selectByCriteria(criteria);
    }

    /**
     * This method returns pages, selected by
     * category_id and search page list
     * @param search_page_id
     * @param maxPages
     * @param ctg_id
     * @return
     */
    public List<Page> showPagesByCategory(int search_page_id, int maxPages, int category_id){
        Criteria criteria = paginationCriteria(search_page_id, maxPages);
        criteria.add(Restrictions.eq("category.category_id", category_id));
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

        pageDao.saveOrUpdate(page);

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
     * Load page by id
     * @param id
     * @return
     */
    @Override
    public Page load(long id) {
        Page page = pageDao.load(Page.class, id);
        return pageDao.merge(page);
    }
}