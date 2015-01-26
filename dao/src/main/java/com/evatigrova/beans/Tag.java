package com.evatigrova.beans;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 *
 */
@Entity
@Table(name = "TAGS")
public class Tag implements Serializable{

    private static final long serialVersionUID = 2L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TAG_ID")
    private int tag_id;

    @Column(name = "TAG_NAME")
    private String tag_name;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},  fetch = FetchType.LAZY)
    @JoinTable(name = "PAGES_TAGS",
    joinColumns=@JoinColumn(name = "TAG_ID"),
    inverseJoinColumns = @JoinColumn(name = "PAGE_ID"))
    private Set<Page> pages;

    public Tag() {
    }

    public int getTag_id() {
        return tag_id;
    }

    public void setTag_id(int tag_id) {
        this.tag_id = tag_id;
    }

    public String getTag_name() {
        return tag_name;
    }

    public void setTag_name(String tag_name) {
        this.tag_name = tag_name;
    }

    public Set<Page> getPages() {
        return pages;
    }

    public void setPages(Set<Page> pages) {
        this.pages = pages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tag)) return false;

        Tag tag = (Tag) o;

        if (tag_id != tag.tag_id) return false;
//        if (!pages.equals(tag.pages)) return false;
        if (!tag_name.equals(tag.tag_name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tag_id;
        result = 31 * result + tag_name.hashCode();
//        result = 31 * result + pages.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "tag_id=" + tag_id +
                ", tag_name='" + tag_name + '\'' +
                '}';
    }
}
