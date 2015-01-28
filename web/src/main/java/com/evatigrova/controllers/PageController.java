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

import java.util.HashSet;
import java.util.Set;

@Controller
public class PageController {

    @Autowired(required = true)
    public ICategoryService categoryService;

    @Autowired(required = true)
    public IPageService pageService;

    @RequestMapping(value = "/admin/page/add", method = RequestMethod.GET)
    public String addPage(
            @RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "category") String category_id,
            @RequestParam(value = "date") String date,
            ModelMap model){

        Page page = new Page();
        Category category = new Category();
        PageDetail pageDetail = new PageDetail();
        Set<Page> pages = new HashSet<Page>();

        int ctg_id = Integer.parseInt(category_id);
        category.setCategory_id(ctg_id);

        page.setCategory(category);
        category.setPages(pages);
        category.getPages().add(page);

        page.setPageDetail(pageDetail);
        pageDetail.setPage(page);


        pageDetail.setDate(date);
        pageDetail.setPage_name(name);

        pageService.save(page);

        return "redirect:/admin";


    }

    @RequestMapping(value = "/admin/page/edit", method = RequestMethod.GET)
    public String editPage(
            @RequestParam(value = "id", required = true) String id,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "category") String category_id,
            @RequestParam(value = "date") String date,
            ModelMap model){



        long page_id = Long.parseLong(id);
        int ctg_id = Integer.parseInt(category_id);

        Page page = pageService.get(page_id);
        Category category = categoryService.get(ctg_id);

        page.setCategory(category);
//        category.getPages().add(page);

        pageService.update(page);

        return "redirect:/admin";

    }




    @RequestMapping(value = "/admin/page/cancel", method = RequestMethod.GET)
    public String mainPage(
            ModelMap model){
        return "redirect:/admin";
    }





}
