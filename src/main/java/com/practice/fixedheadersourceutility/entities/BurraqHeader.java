package com.practice.fixedheadersourceutility.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author ubaid khanzada
 * @since 1.0
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BurraqHeader {

    @JsonIgnore
    private int cmsHeaderId;

    private String headerId;

    private final String name;

    private String industryId;

    private boolean isIndustryLevel;

    /**
     * Constructs an instance of an header
     *
     * @param name name
     */
    @JsonCreator
    public BurraqHeader(@JsonProperty("name") final String name) {
        this.name = name;
    }

    /**
     * @return the isIndustryLevel
     */
    @JsonProperty("isIndustryLevel")
    public boolean isIndustryLevel() {
        return isIndustryLevel;
    }

    /**
     * @param isIndustryLevel the isIndustryLevel to set
     */
    @JsonProperty("isIndustryLevel")
    public void setIndustryLevel(final boolean isIndustryLevel) {
        this.isIndustryLevel = isIndustryLevel;
    }

    /**
     * @param industryId the industryId to set
     */
    public void setIndustryId(final String industryId) {
        this.industryId = industryId;
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
     * @return the cmsHeaderId
     */
    public int getCmsHeaderId() {
        return cmsHeaderId;
    }

    /**
     * @param cmsHeaderId the cmsHeaderId to set
     */
    public void setCmsHeaderId(final int cmsHeaderId) {
        this.cmsHeaderId = cmsHeaderId;
    }

    /**
     * @return the headerId
     */
    public String getHeaderId() {
        return headerId;
    }

    /**
     * @param headerId the headerId to set
     */
    public void setHeaderId(final String headerId) {
        this.headerId = headerId;
    }
}