package com.evatigrova.services;

import com.evatigrova.beans.Page;

import java.util.List;

public interface IPageService  {

    public Page createDtoPage();

    public List<Page> getAllPages(int search_int, String category, int maxPages);

//    public List<Page> getAllPages(int search_int, int maxPages);

//    //** pagination with category
//    public List<Integer> getPaginationList(String search_page, int category_id, int pageSize);
//
//    //** pagination without category
//    public List<Integer> getPaginationList(String search_page, int pageSize);

    public List<Integer> getPaginationList(int search_int, int pageSize, long numberOfPages);

    //** select for pagination with category
    public Long selectNumberOfPages(String category);

//    //** select for pagination without category
//    public Long selectNumberOfPages();

    public boolean save(Page page);

    public boolean update(Page page);

    public boolean deletePage(Page page);

    public Page get(long id);

    public Page load(long id);
}
