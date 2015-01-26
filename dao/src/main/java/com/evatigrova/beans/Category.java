package com.evatigrova.beans;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 */
@Entity
@Table(name="CATEGORIES")

public class Category implements Serializable {

    private static final long serialVersionUID = 3L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CATEGORY_ID")
    private int category_id;

    @Column(name="CATEGORY_NAME")
    private String category_name;

    @OneToMany(mappedBy = "category")
    private Set<Page> pages;
    public Category() {
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
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
        if (!(o instanceof Category)) return false;

        Category category = (Category) o;

        if (category_id != category.category_id) return false;
        if (!category_name.equals(category.category_name)) return false;
//        if (pages != null ? !pages.equals(category.pages) : category.pages != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = category_id;
        result = 31 * result + category_name.hashCode();
//        result = 31 * result + (pages != null ? pages.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Category{" +
                "category_id=" + category_id +
                ", category_name='" + category_name + '\'' +
                '}';
    }
}
