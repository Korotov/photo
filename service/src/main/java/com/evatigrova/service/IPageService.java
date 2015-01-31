package com.evatigrova.service;

import com.evatigrova.beans.Page;

import java.util.List;

public interface IPageService  {

    public Page createDtoPage();

    public List<Page> getAllPages(String search_result, String category, int maxPages);

    public List<Page> getAllPages(String search_result, int maxPages);

    public boolean save(Page page);

    public boolean update(Page page);

    public boolean deletePage(Page page);

    public Page get(long id);

    public Page load(long id);
}
