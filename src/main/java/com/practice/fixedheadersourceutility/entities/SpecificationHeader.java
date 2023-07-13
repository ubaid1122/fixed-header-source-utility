package com.practice.fixedheadersourceutility.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author ubaid khanzada
 * @since 1.0
 */
public class SpecificationHeader {

    private String headerId;

    private Source source; //INDUSTRY, INHERITED and SELF

    private Integer rank;


    /**
     * Constructor for header associated with category
     *
     * @param source stores source of attribute
     */
    @JsonCreator
    public SpecificationHeader(@JsonProperty("source") final Source source) {
        this.source = source;
    }

    /**
     * @return the rank
     */
    public Integer getRank() {
        return rank;
    }

    /**
     * @param rank the rank to set
     */
    public void setRank(final Integer rank) {
        this.rank = rank;
    }

    /**
     * @param headerId the headerId to set
     */
    public void setHeaderId(final String headerId) {
        this.headerId = headerId;
    }

    /**
     * @param source the source to set
     */
    public void setSource(final Source source) {
        this.source = source;
    }

    /**
     * Returns headerId
     *
     * @return headerId
     */
    public String getHeaderId() {
        return this.headerId;
    }

    /**
     * Returns attribute source
     *
     * @return source
     */
    public Source getSource() {
        return this.source;
    }
}

