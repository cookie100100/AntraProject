package org.example.antraproject1.controller;

import org.example.antraproject1.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping("/search")
    public CompletableFuture<String> getUserData() {
        return searchService.getTeacherStudentData();
    }
}
