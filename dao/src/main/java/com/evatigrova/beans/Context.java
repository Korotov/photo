package com.evatigrova.beans;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 *
 */
@Entity
@Table(name = "CONTEXT")
public class Context implements Serializable, Comparable{

    private static final long serialVersionUID = 31L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CONTEXT_ID", unique = true)
    private long context_id;

    @Column(name = "CONTEXT_TYPE")
    private String context_type;

    @Column(name = "VALUE")
    private String value;

    @Column(name = "SORT_INDEX")
    private int sort_index;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_PAGEDETAIL_ID")
    private PageDetail pageDetail;

    public Context() {
    }

    public int getSort_index() {
        return sort_index;
    }

    public void setSort_index(int sort_index) {
        this.sort_index = sort_index;
    }

    public long getContext_id() {
        return context_id;
    }

    public void setContext_id(long context_id) {
        this.context_id = context_id;
    }

    public PageDetail getPageDetail() {
        return pageDetail;
    }

    public void setPageDetail(PageDetail pageDetail) {
        this.pageDetail = pageDetail;
    }

    public String getContext_type() {
        return context_type;
    }

    public void setContext_type(String context_type) {
        this.context_type = context_type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Context)) return false;

        Context context = (Context) o;

        if (context_id != context.context_id) return false;
        if (context_type != null ? !context_type.equals(context.context_type) : context.context_type != null)
            return false;
        if (value != null ? !value.equals(context.value) : context.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (context_id ^ (context_id >>> 32));
        result = 31 * result + (context_type != null ? context_type.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Object obj) {
        Context context = (Context) obj;
        if (this.getSort_index()>context.getSort_index()) {
            return 1;
        }
        else
        if (this.getSort_index()<context.getSort_index()) {
            return -1;
        }
        else return 0;
    }


}
