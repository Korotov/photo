package com.evatigrova.beans;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 *
 */
@Entity
@Table(name="PAGES")
public class Page implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PAGE_ID")
    private long page_id;

    @OneToOne(fetch=FetchType.EAGER, mappedBy = "page", cascade = CascadeType.ALL)
    private PageDetail pageDetail;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "FK_CATEGORY_ID")
    private Category category;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "PAGES_TAGS",
    joinColumns = @JoinColumn(name = "PAGE_ID"),
    inverseJoinColumns = @JoinColumn(name = "TAG_ID"))
    private Set<Tag> tags;

    public Page() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getPage_id() {
        return page_id;
    }

    public void setPage_id(long page_id) {
        this.page_id = page_id;
    }

    public PageDetail getPageDetail() {
        return pageDetail;
    }

    public void setPageDetail(PageDetail pageDetail) {
        this.pageDetail = pageDetail;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Page)) return false;

        Page page = (Page) o;

        if (page_id != page.page_id) return false;
//        if (category != null ? !category.equals(page.category) : page.category != null) return false;
//        if (pageDetail != null ? !pageDetail.equals(page.pageDetail) : page.pageDetail != null) return false;
//        if (tags != null ? !tags.equals(page.tags) : page.tags != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (page_id ^ (page_id >>> 32));
//        result = 31 * result + (pageDetail != null ? pageDetail.hashCode() : 0);
//        result = 31 * result + (category != null ? category.hashCode() : 0);
//        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Page{" +
                "page_id=" + page_id +
                '}';
    }
}
