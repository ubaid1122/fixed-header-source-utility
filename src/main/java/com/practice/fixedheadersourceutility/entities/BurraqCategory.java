package com.practice.fixedheadersourceutility.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;

/**
 * @author ubaid khanzada
 * @since 1.0
 */
public class BurraqCategory {

    private final String name;

    private String industryId;

    private String parentCategoryId;

    private String description;

    private String id;

    private CategoryStatus status = CategoryStatus.ACTIVE;

    private Set<SpecificationHeader> headers = new HashSet<>();


    /**
     * Constructs an instance of an BurraqCategory
     *
     * @param name category name
     */
    @JsonCreator
    public BurraqCategory(@JsonProperty("name") final String name) {
        this.name = name;
    }

    public void addSpecificationHeader(final SpecificationHeader model) {
        headers.add(model);
    }


    /**
     * @return the headers
     */
    public Set<SpecificationHeader> getHeaders() {
        return headers;
    }

    /**
     * @param industryId the industryId to set
     */
    public void setIndustryId(final String industryId) {
        this.industryId = industryId;
    }

    /**
     * @return the status
     */
    public CategoryStatus getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(final CategoryStatus status) {
        this.status = status;
    }

    /**
     * @param headers the headers to set
     */
    public void setHeaders(final Set<SpecificationHeader> headers) {
        this.headers = headers;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the industryId
     */
    public String getIndustryId() {
        return industryId;
    }

    /**
     * @return the parent category id
     */
    public String getParentCategoryId() {
        return parentCategoryId;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param parentCategoryId set the parent category id
     */
    public void setParentCategoryId(String parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

}