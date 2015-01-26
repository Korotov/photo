package com.evatigrova.service;

import com.evatigrova.beans.Page;

import java.util.List;

public interface IPageService  {

    public List<Page> getPagesByCategory(int category_id);

    public List<Page> showAllPages(int search_page_id, int maxPages);

    public boolean save(Page page);

    public boolean deletePage(Page page);
}
