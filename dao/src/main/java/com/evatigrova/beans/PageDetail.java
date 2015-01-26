package com.evatigrova.beans;


import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 *
 */
@Entity
@Table(name = "PAGEDETAILS")
public class PageDetail {

    private static final long serialVersionUID = 28L;
    @GenericGenerator(
            name = "generator",
            strategy = "foreign",
            parameters = @Parameter(name = "property", value = "page")
    )
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "PAGE_ID", unique = true)
    private long page_id;

    @Column(name = "PAGE_NAME")
    private String page_name;

    @Column(name = "DATE")
    private String date;

    @Column(name = "VISIBLE")
    private boolean visible=false;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Page page;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pageDetail")
    private Collection<Context> contexts;



    public PageDetail() {
    }

    public String getPage_name() {
        return page_name;
    }

    public void setPage_name(String page_name) {
        this.page_name = page_name;
    }

    public Collection<Context> getContexts() {
        return contexts;
    }

    public void setContexts(Collection<Context> contexts) {
        this.contexts = contexts;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public long getPage_id() {
        return page_id;
    }

    public void setPage_id(long page_id) {
        this.page_id = page_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PageDetail)) return false;

        PageDetail that = (PageDetail) o;

        if (page_id != that.page_id) return false;
        if (visible != that.visible) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (page != null ? !page.equals(that.page) : that.page != null) return false;
        if (page_name != null ? !page_name.equals(that.page_name) : that.page_name != null) return false;


        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (page_id ^ (page_id >>> 32));
        result = 31 * result + (page_name != null ? page_name.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (visible ? 1 : 0);
        result = 31 * result + (page != null ? page.hashCode() : 0);
        return result;
    }
}
