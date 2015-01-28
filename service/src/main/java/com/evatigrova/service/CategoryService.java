package com.evatigrova.service;

import com.evatigrova.beans.Category;
import com.evatigrova.beans.Page;
import com.evatigrova.dao.Dao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 */
@Service
@Transactional
public class CategoryService implements ICategoryService{

    public static Logger log = LogManager.getLogger(CategoryService.class);
    List<Category> categories = new ArrayList<Category>();

    @Autowired
    public Dao<Category> categoryDao;

    public CategoryService() {
    }

    @Override
    public void save(Category category) {
        categoryDao.save(category);
    }

    @Override
    public void update(Category category) {
        categoryDao.update(category);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public Category load(int id) {
        return categoryDao.load(Category.class, id);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public Category get(int id) {
        return categoryDao.get(Category.class, id);
    }

    @Override
    public List<Category> getCategories() {
        String selectAllCategories = "SELECT C FROM com.evatigrova.beans.Category C ORDER BY C.category_name ASC";
        Query query = categoryDao.getSession().createQuery(selectAllCategories);

        return categoryDao.selectByHQL(query);
    }

    /**
     * This method copy all pages of deleting
     * category to category with index one
     * @param id
     */
    @Override
    public void delete(int id){

        Category persistentCategory = categoryDao.load(Category.class, id);
        Category categoryForPagesWCategory = categoryDao.load(Category.class, 1);
        Set<Page> pageSet = persistentCategory.getPages();
        for (Page page : pageSet) {
            page.setCategory(categoryForPagesWCategory);
        }
        pageSet.clear();

        categoryDao.delete(persistentCategory);

    }

    @Override
    public void changeCategoryName(int category_id, String name) {

        Category category = load(category_id);
        category.setCategory_name(name);
        update(category);
    }
}        