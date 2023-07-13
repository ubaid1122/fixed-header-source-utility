package com.practice.fixedheadersourceutility.service;

import com.practice.fixedheadersourceutility.client.BurraqServiceClient;
import com.practice.fixedheadersourceutility.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author ubaid khanzada
 * @since 1.0
 */
@Service
public class FixedHeaderSourceServiceImpl implements FixedHeaderSourceService{

    private BurraqServiceClient burraqServiceClient;

    @Autowired
    public FixedHeaderSourceServiceImpl(BurraqServiceClient burraqServiceClient) {
        this.burraqServiceClient = burraqServiceClient;
    }

    @Override
    public void fixedHeaderSourceInCategory(final String categoryId) {
        BurraqCategory category = burraqServiceClient.getCodePlanByCategoryId
                (categoryId, "en", "BOTH", false);
        BurraqCategory parentCategory = null;
        if (category.getParentCategoryId() != null) {
            parentCategory = burraqServiceClient.getCodePlanByCategoryId
                    (category.getParentCategoryId(), "en", "BOTH", false);
        }
        if (category.getHeaders() != null) {
            Set<SpecificationHeader> headers = category.getHeaders();
            for (SpecificationHeader specHeader : headers) {
                if (specHeader.getSource().equals(Source.SELF)) {
                BurraqHeader header = burraqServiceClient.findHeaderById(specHeader.getHeaderId());
                if (header.isIndustryLevel()) {
                    updateCategoryHeaderSource
                            (specHeader, Source.INDUSTRY, categoryId, "fixedHeaderSourceUtility");
                } else if (parentCategory != null) {
                    boolean isHeaderPresent = parentCategory.getHeaders().stream()
                            .anyMatch(obj -> obj.getHeaderId().equals(specHeader.getHeaderId()));
                    if (isHeaderPresent) {
                        updateCategoryHeaderSource
                                (specHeader, Source.INHERITED, categoryId, "fixedHeaderSourceUtility");
                    }
                }
             }
            }
        }
    }

    private void updateCategoryHeaderSource(final SpecificationHeader specificationHeader,
                                            final Source source, final String categoryId, final String performedBy) {

        final SpecificationHeader updateHeader = new SpecificationHeader(source);
        updateHeader.setHeaderId(specificationHeader.getHeaderId());
        updateHeader.setRank(specificationHeader.getRank());
        final CategoryHeaderRequest request = new CategoryHeaderRequest(performedBy);
        request.setCategoryHeader(updateHeader);
        burraqServiceClient.updateCategoryHeader(categoryId, specificationHeader.getHeaderId(), request);
    }
}
