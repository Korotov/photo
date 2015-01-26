package com.evatigrova.service;

import com.evatigrova.beans.Category;

import java.util.List;

public interface ICategoryService  {

    public List<Category> getCategories();

    public Category load(int id);

    public void save(Category category);

    public void update(Category category);

    /**
     * This method copy all pages of deleting
     * category to category with index one
     * @param category
     */
    public void delete(int id);

    public void changeCategoryName(int category_id, String name);
}
