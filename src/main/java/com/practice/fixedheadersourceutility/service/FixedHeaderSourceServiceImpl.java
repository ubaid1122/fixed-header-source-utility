package com.practice.fixedheadersourceutility.service;

import com.practice.fixedheadersourceutility.client.BurraqServiceClient;
import com.practice.fixedheadersourceutility.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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
    public void fixedHeaderSourceByIndustryId(final String industryId) {
        Set<String> categories = getAllCategoriesByIndustryId(industryId);
        for (String categoryId: categories) {
            fixedHeaderSourceByCategoryId(categoryId);
        }
    }

    private Set<String> getAllCategoriesByIndustryId(final String industryId) {
        Object object = burraqServiceClient.findAllCategoriesByIndustryId(industryId, "basicInfo", 0, 1000);

        return Optional.ofNullable(object)
                .filter(obj -> obj instanceof LinkedHashMap)
                .map(obj -> (LinkedHashMap<String, Object>) obj)
                .map(responseMap -> (LinkedHashMap<String, Object>) responseMap.get("_embedded"))
                .map(embedded -> (List<Object>) embedded.get("categories"))
                .stream()
                .flatMap(Collection::stream)
                .filter(obj -> obj instanceof LinkedHashMap)
                .map(obj -> (LinkedHashMap<String, Object>) obj)
                .map(categoryMap -> (LinkedHashMap<String, Object>) categoryMap.get("_links"))
                .map(nestedMap -> (LinkedHashMap<String, Object>) nestedMap.get("self"))
                .map(href -> (String) href.get("href"))
                .map(this::extractValueFromURL)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }

    @Override
    public void fixedHeaderSourceByCategoryId(String categoryId) {
        try {
            BurraqCategory category = burraqServiceClient.getCodePlanByCategoryId
                    (categoryId, "en", "BOTH", false);
            BurraqCategory parentCategory = null;
            if (category != null && category.getParentCategoryId() != null) {
                parentCategory = burraqServiceClient.getCodePlanByCategoryId
                        (category.getParentCategoryId(), "en", "BOTH", false);
            }
            if (category != null && category.getHeaders() != null) {
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
        } catch (Exception e) {
            // do not throw exception
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

    public String extractValueFromURL(String url) {
        int lastIndex = url.lastIndexOf("/");
        if (lastIndex != -1 && lastIndex < url.length() - 1) {
            return url.substring(lastIndex + 1);
        }
        return null; // or throw an exception, depending on your use case
    }
}
