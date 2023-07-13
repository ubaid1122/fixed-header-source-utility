package com.practice.fixedheadersourceutility.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

/**
 * @author ubaid khanzada
 * @since 1.0
 */
public class CategoryHeaderRequest {

    private final String lastModifiedBy;

    @JsonUnwrapped
    private SpecificationHeader categoryHeader;

    /**
     * Constructs categoryHeader object
     * Request body json like
     * {"headerId" : "61a66d950798610001e68e2e","source" : "SELF",
     * "rank" : 280}
     * @param lastModifiedBy last modified by
     * */
    @JsonCreator
    public CategoryHeaderRequest(
            @JsonProperty("lastModifiedBy") final String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    /**
     * Return the last modified by
     *
     * @return the String
     */
    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public SpecificationHeader getCategoryHeader() {
        return categoryHeader;
    }

    public void setCategoryHeader(SpecificationHeader categoryHeader) {
        this.categoryHeader = categoryHeader;
    }
}