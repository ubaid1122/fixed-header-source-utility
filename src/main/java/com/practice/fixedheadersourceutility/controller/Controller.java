package com.practice.fixedheadersourceutility.controller;

import com.practice.fixedheadersourceutility.service.FixedHeaderSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ubaid khanzada
 * @since 1.0
 */
@RestController
public class Controller {

    private FixedHeaderSourceService fixedHeaderSourceService;

    @Autowired
    public Controller(FixedHeaderSourceService fixedHeaderSourceService) {
        this.fixedHeaderSourceService = fixedHeaderSourceService;
    }


    @RequestMapping("/api/fixed-headers/industry/{industryId}")
    public ResponseEntity<String> fixedHeaderSourceByIndustry(@PathVariable String industryId) {
        fixedHeaderSourceService.fixedHeaderSourceByIndustryId(industryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @RequestMapping("/api/fixed-headers/category/{categoryId}")
    public ResponseEntity<String> fixedHeaderSourceByCategory(@PathVariable String categoryId) {
        fixedHeaderSourceService.fixedHeaderSourceByCategoryId(categoryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
