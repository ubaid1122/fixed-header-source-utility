package com.practice.fixedheadersourceutility.client;

import com.practice.fixedheadersourceutility.entities.BurraqCategory;
import com.practice.fixedheadersourceutility.entities.BurraqHeader;
import com.practice.fixedheadersourceutility.entities.CategoryHeaderRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

/**
 * @author ubaid khanzada
 * @since 1.0
 */
@Component
@FeignClient(value = "burraq-service-client", url = "${services.bag.url}")
public interface BurraqServiceClient {

    /**
     * Method to get codeplan
     *
     * @param categoryId the categoryId
     * @param type optional type
     * @param loadAttributeSubObjects optional loadAttributeSubObjects
     * @return Category
     */
    @GetMapping("/categories/{categoryId}/codeplan")
    BurraqCategory getCodePlanByCategoryId(
            @PathVariable("categoryId") final String categoryId,
            @RequestParam(value = "languageId", required = false) final String languageId,
            @RequestParam(value = "type") final String type,
            @RequestParam(value = "loadAttributeSubObjects") final Boolean loadAttributeSubObjects);

    @GetMapping("/headers/{headerId}")
    BurraqHeader findHeaderById(@PathVariable("headerId") final String headerId);

    @PutMapping("/categories/{id}/headers/{headerId}")
    ResponseEntity<Object> updateCategoryHeader(@PathVariable(value = "id") String id,
                                                @PathVariable(value = "headerId") String headerId,
                                                @RequestBody CategoryHeaderRequest request);

}
