package com.evatigrova.controllers;


import com.evatigrova.beans.Page;
import com.evatigrova.services.ICategoryService;
import com.evatigrova.services.IPageService;
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
    public String addPage(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "category") int category_id,
            @RequestParam(value = "date") String date,
            ModelMap model){

        Page page = pageService.createDtoPage();

        page.getPageDetail().setDate(date);
        page.getPageDetail().setPage_name(name);
        page.getCategory().setCategory_id(category_id);

        pageService.save(page);

        return "redirect:/admin";


    }

    @RequestMapping(value = "/admin/page/edit", method = RequestMethod.GET)
    public String editPage(
            @RequestParam(value = "id", required = true) long id,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "category") int category_id,
            @RequestParam(value = "date") String date,
            ModelMap model){

        Page page = pageService.createDtoPage();

        page.setPage_id(id);
        page.getCategory().setCategory_id(category_id);
        page.getPageDetail().setDate(date);
        page.getPageDetail().setPage_name(name);

        pageService.update(page);

        return "redirect:/admin";

    }




    @RequestMapping(value = "/admin/page/cancel", method = RequestMethod.GET)
    public String mainPage(
            ModelMap model){
        return "redirect:/admin";
    }





}
