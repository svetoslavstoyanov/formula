package com.formula.one.graphql.input;

import org.springframework.data.domain.Sort;

public class SortOrder {
    public Sort.Direction direction = null;
    public String field = null;

    public SortOrder(Sort.Direction direction, String field) {
        this.direction = direction;
        this.field = field;
    }

    public SortOrder() {
    }

    public Sort.Direction getDirection() {
        return direction;
    }

    public void setDirection(Sort.Direction direction) {
        this.direction = direction;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
