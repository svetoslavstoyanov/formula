package com.formula.one.graphql.resource;

import org.springframework.data.domain.Page;

import java.util.List;

public class PageResource<Object> {

    private List<Object> data;
    private Integer totalPages;
    private Long totalElements;
    private Boolean isFirst;
    private Boolean isLast;
    private boolean hasNext;
    private boolean hasPrevious;

    public PageResource() {
    }

    public PageResource(Page<Object> page) {
        this.data = page.getContent();
        this.totalPages = page.getTotalPages();
        this.totalElements = page.getTotalElements();
        this.isFirst = page.isFirst();
        this.isLast = page.isLast();
        this.hasNext = page.hasNext();
        this.hasPrevious = page.hasPrevious();
    }

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    public Boolean getFirst() {
        return isFirst;
    }

    public void setFirst(Boolean first) {
        isFirst = first;
    }

    public Boolean getLast() {
        return isLast;
    }

    public void setLast(Boolean last) {
        isLast = last;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public boolean isHasPrevious() {
        return hasPrevious;
    }

    public void setHasPrevious(boolean hasPrevious) {
        this.hasPrevious = hasPrevious;
    }
}
