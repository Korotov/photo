package com.evatigrova.controllers;


import com.evatigrova.beans.Category;
import com.evatigrova.service.ICategoryService;
import com.evatigrova.service.IPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {
    public static final int maxResults = 3;

    @Autowired(required = true)
    public ICategoryService categoryService;

    @Autowired(required = true)
    public IPageService pageService;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String mainPage(
            ModelMap model){



        model.put("categoryList", categoryService.getCategories());
//        model.put("pageList", pageService.getAllPages());

        return "admin";
    }

    @RequestMapping(value = "admin/add_category", method = RequestMethod.GET)
    public String addCategory(@RequestParam("category_edit") String category_id,
                              @RequestParam("action") String action,
                              @RequestParam("category_name") String category_name,
                           ModelMap model){
        Category category=null;
        if ("add".equals(action)) {
            category = new Category();
        }
        if("edit".equals(action)) {
            int id = Integer.parseInt(category_id);
            category = categoryService.load(id);
        }
        category.setCategory_name(category_name);




        return "redirect:/admin";
    }




}
