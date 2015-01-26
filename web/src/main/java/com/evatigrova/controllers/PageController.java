package com.evatigrova.controllers;


import com.evatigrova.beans.Category;
import com.evatigrova.beans.Page;
import com.evatigrova.beans.PageDetail;
import com.evatigrova.service.ICategoryService;
import com.evatigrova.service.IPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {

    @Autowired(required = true)
    public ICategoryService categoryService;

    @Autowired(required = true)
    public IPageService pageService;

    @RequestMapping(value = "/admin/page/add", method = RequestMethod.GET)
    public String mainPage(
            @RequestParam(value = "id", required = false) int id,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "category") int category_id,
            @RequestParam(value = "date") String date,
            @RequestParam(value = "action") String action,
            ModelMap model){

        Page page = new Page();
        Category category = new Category();
        PageDetail pageDetail = new PageDetail();

        category.setCategory_id(category_id);
        page.setCategory(category);
        page.setPageDetail(pageDetail);
        pageDetail.setDate(date);
        pageDetail.setPage_name(name);


        if ( ((Integer)id)!=null) {
            page.setPage_id(id);
        }

        pageService.save(page);

        return "admin";
    }





}
