package com.assignment.freelancer.search;

import com.assignment.freelancer.search.dto.FreelancersResponse;
import com.assignment.freelancer.search.dto.HotKeywordsResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
@Tag(name = "Search", description = "검색 API Document")
public class SearchApiController {
    private final SearchService searchService;

    public SearchApiController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping
    @Operation(summary = "키워드로 프리랜서 검색 API", description = "키워드로 해당 기술역량을 가진 프리랜서 목록을 검색합니다")
    public ResponseEntity<FreelancersResponse> searchFreelancers(@RequestParam("keyword") String keyword,
                                                                 @RequestParam("sort") FreelancerSortType sortType,
                                                                 @RequestParam("page") int page,
                                                                 @RequestParam("size") int size) {
        FreelancersResponse result = searchService.search(keyword, sortType, page, size);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/hot")
    @Operation(summary = "인기 검색어 조회 API", description = "인기 검색어 키워드를 조회합니다")
    public ResponseEntity<HotKeywordsResponse> getHotKeywords() {
        HotKeywordsResponse result = searchService.getHotKeywords();

        return ResponseEntity.ok(result);
    }
}
