package com.evatigrova.service;

import com.evatigrova.beans.Category;
import com.evatigrova.beans.Page;
import com.evatigrova.dao.Dao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
        categoryDao.saveOrUpdate(category);
    }

    public Category load(int id) {
        return categoryDao.load(Category.class, id);
    }

    public List<Category> getCategories() {
        String selectAllCategories = "SELECT C FROM com.evatigrova.beans.Category C ORDER BY C.category_name DESC";
        Query query = categoryDao.getSession().createQuery(selectAllCategories);

        return categoryDao.selectByHQL(query);
    }

    /**
     * This method copy all pages of deleting
     * category to category with index one
     * @param category
     */
    public void delete(Category category){
        if(category!=null) {
            Category persistentCategory = categoryDao.load(Category.class, category.getCategory_id());
            Category categoryForPagesWCategory = categoryDao.load(Category.class, 1);
            Set<Page> pageSet = persistentCategory.getPages();
            for (Page page : pageSet) {
                page.setCategory(categoryForPagesWCategory);
            }
            pageSet.clear();

            categoryDao.delete(persistentCategory);
        }
    }
}        