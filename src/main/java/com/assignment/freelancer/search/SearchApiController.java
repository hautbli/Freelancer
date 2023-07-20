package com.assignment.freelancer.search;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
public class SearchApiController {
    private final SearchService searchService;

    public SearchApiController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping
    public ResponseEntity<FreelancersResponse> searchFreelancers(@RequestParam("keyword") String keyword,
                                                                 @RequestParam("sort") FreelancerSortType sortType,
                                                                 @RequestParam("page") int page,
                                                                 @RequestParam("size") int size) {
        FreelancersResponse result = searchService.search(keyword, sortType, page, size);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/hot")
    public ResponseEntity<KeywordsResponse> getHotKeywords() {
        KeywordsResponse result = searchService.getHotKeywords();

        return ResponseEntity.ok(result);
    }
}
