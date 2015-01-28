package com.evatigrova.controllers;


import com.evatigrova.beans.Category;
import com.evatigrova.beans.Page;
import com.evatigrova.service.ICategoryService;
import com.evatigrova.service.IPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {
    public static final int maxResults = 5;

    @Autowired(required = true)
    public ICategoryService categoryService;

    @Autowired(required = true)
    public IPageService pageService;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String mainPage(
            ModelMap model){

        model.put("categories", categoryService.getCategories());
        model.put("pageList", pageService.getAllPages(maxResults));

        return "admin";
    }

    @RequestMapping(value = "admin/add/category", method = RequestMethod.GET)
    public String manageCategory(@RequestParam(value = "category_edit", required = false) String category_id,
                              @RequestParam("action") String action,
                              @RequestParam("category_name") String category_name,
                           ModelMap model){
        Category category=null;
        if ("add".equals(action)) {
            category = new Category();
            category.setCategory_name(category_name);
            categoryService.save(category);
        }
        if("edit".equals(action)) {
            int id = Integer.parseInt(category_id);
            categoryService.changeCategoryName(id, category_name);
        }

        if("delete".equals(action)) {
            int id = Integer.parseInt(category_id);
            categoryService.delete(id);

        }

        return "redirect:/admin";
    }

    @RequestMapping(value = "admin/new/page", method = RequestMethod.POST)
    public String newPage( ModelMap model) {
        model.put("action", "add");
        model.put("categories", categoryService.getCategories());
        return "page";
    }

    @RequestMapping(value = "admin/edit/page/{id}", method = RequestMethod.GET)
    public String editPage(
//            @RequestParam(value = "id" ) long id,
            @PathVariable long id,
            ModelMap model) {

        Page page = pageService.get(id);

        model.put("page", page);
        model.put("action", "edit");
        model.put("categories", categoryService.getCategories());
        return "page";
    }




}
