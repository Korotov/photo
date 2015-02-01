package com.evatigrova.utils;

import java.util.List;

/**
 *
 */
public interface IPager {

    //** use this method for receiving list of pagination numbers. This numbers is used in view
    public List<Integer> getList(long numberOfPages, int search_int, int pageSize);
}
