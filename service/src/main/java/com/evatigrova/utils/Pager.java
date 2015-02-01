package com.evatigrova.utils;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 */
@Component("pager")
public class Pager implements IPager{

    public int size(long numberOfPages, int pageSize) {
        return (int)Math.ceil((numberOfPages-1)/pageSize)+1;
    }

    /**
     * use this method for receiving list of pagination numbers.
     * This numbers are used in view
     * @param numberOfPages
     * @param search_int
     * @param pageSize
     * @return
     */
    public List<Integer> getList(long numberOfPages, int search_int, int pageSize) {
        //**bigSize - number of all pageLists
        int bigSize = size(numberOfPages, pageSize);
        List<Integer> list = new ArrayList<Integer>(5);
        if (search_int<1){
            search_int=1;
        }
        if (search_int>bigSize) {
            search_int=bigSize;
        }
        list.add(search_int);

        int size = bigSize;
        if (size>5) {
            size=5;
        }
        //** every turn of cycle step will be increased by 1
        int step=1;

//         pagination number will be written
//         if it is more then 0 and less then bigSize
        for (int i=0;; i++, step++) {
            int elem1 = search_int-step;
            if(elem1>=1) {
                list.add(elem1);
            }
            if (list.size()>=size) break;

            int elem2 = search_int+step;
            if(elem2<=bigSize) {
                list.add(elem2);
            }
            if (list.size()>=size) break;
        }
        Collections.sort(list, new IntComparator());

        return list;
    }






}
