package com.assignment.freelancer.search;

import com.assignment.freelancer.domain.Freelancer;
import com.assignment.freelancer.domain.FreelancerKeyword;
import com.assignment.freelancer.domain.FreelancerKeywordRepository;
import com.assignment.freelancer.domain.FreelancerRepository;
import com.assignment.freelancer.domain.Keyword;
import com.assignment.freelancer.domain.KeywordRepository;
import com.assignment.freelancer.search.dto.FreelancerResponse;
import com.assignment.freelancer.search.dto.FreelancersResponse;
import com.assignment.freelancer.search.dto.HotKeywordResponse;
import com.assignment.freelancer.search.dto.KeywordResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SearchServiceTest {
    @Autowired
    private SearchService searchService;

    @Autowired
    private FreelancerRepository freelancerRepository;
    @Autowired
    private FreelancerKeywordRepository freelancerKeywordRepository;
    @Autowired
    private KeywordRepository keywordRepository;

    private List<Freelancer> freelancers;
    private List<Keyword> keywords;

    @BeforeEach
    void setUp() {
        freelancers = freelancerRepository.saveAll(List.of(
                new Freelancer(null, 40L),
                new Freelancer(null, 30L),
                new Freelancer(null, 50L)
        ));

        keywords = keywordRepository.saveAll(List.of(
                new Keyword("k1", 80L),
                new Keyword("y1", 10L),
                new Keyword("k2", 20L),
                new Keyword("y2", 40L),
                new Keyword("k3", 70L),
                new Keyword("y3", 30L),
                new Keyword("a", 90L),
                new Keyword("b", 50L),
                new Keyword("c", 60L),
                new Keyword("d", 100L),
                new Keyword("e", 200L)

        ));

        List<FreelancerKeyword> freelancerKeywords = List.of(
                new FreelancerKeyword(freelancers.get(0), keywords.get(0), 7),
                new FreelancerKeyword(freelancers.get(0), keywords.get(1), 4),
                new FreelancerKeyword(freelancers.get(1), keywords.get(2), 11),
                new FreelancerKeyword(freelancers.get(1), keywords.get(3), 2),
                new FreelancerKeyword(freelancers.get(2), keywords.get(4), 8),
                new FreelancerKeyword(freelancers.get(2), keywords.get(5), 3)
        );

        freelancerKeywordRepository.saveAll(freelancerKeywords);
    }

    @AfterEach
    void tearDown() {
        freelancerKeywordRepository.deleteAll();
        freelancerRepository.deleteAll();
        keywordRepository.deleteAll();
    }

    @Test
    @DisplayName("키워드를 검색하면 검색한 키워드를 갖고 있는 프리랜서가 리턴된다")
    void searchWithKeyword() {
        // when
        FreelancersResponse response = searchService.search("k", FreelancerSortType.LATEST, 0, 2);

        // then
        List<FreelancerResponse> freelancersResponses = response.getFreelancersResponses();

        List<Long> expectedFreelancerIds = List.of(freelancers.get(2).getId(), freelancers.get(1).getId());
        Map<Long, List<String>> keywordNamesByFreelancerId = Map.of(
                expectedFreelancerIds.get(0), List.of("k3", "y3"),
                expectedFreelancerIds.get(1), List.of("k2", "y2")
        );
        for (int i = 0; i < freelancersResponses.size(); i++) {
            FreelancerResponse freelancer = freelancersResponses.get(i);
            assertThat(freelancer.getId()).isEqualTo(expectedFreelancerIds.get(i));

            List<String> actualKeywordsNames = freelancer.getKeywords().stream()
                    .map(KeywordResponse::getName)
                    .toList();
            List<String> expectedKeywordNames = keywordNamesByFreelancerId.get(freelancer.getId());
            assertThat(actualKeywordsNames).isEqualTo(expectedKeywordNames);
        }
    }

    @Test
    @DisplayName("키워드를 검색 시, 기술능력순으로 정렬할 수 있다.")
    void searchWithKeywordSortByAbilityLevel() {
        // when
        FreelancersResponse response = searchService.search("k", FreelancerSortType.ABILITY_LEVEL, 0, 2);

        // then
        List<FreelancerResponse> freelancersResponses = response.getFreelancersResponses();

        List<Long> freelancerIds = List.of(freelancers.get(1).getId(), freelancers.get(2).getId());

        for (int i = 0; i < freelancersResponses.size(); i++) {
            FreelancerResponse freelancer = freelancersResponses.get(i);
            assertThat(freelancer.getId()).isEqualTo(freelancerIds.get(i));
        }
    }

    @Test
    @DisplayName("키워드를 검색 시, 최신순으로 정렬할 수 있다.")
    void searchWithKeywordSortByLatest() {
        // when
        FreelancersResponse response = searchService.search("k", FreelancerSortType.LATEST, 0, 2);

        // then
        List<FreelancerResponse> freelancersResponses = response.getFreelancersResponses();

        List<Long> freelancerIds = List.of(freelancers.get(2).getId(), freelancers.get(1).getId());

        for (int i = 0; i < freelancersResponses.size(); i++) {
            FreelancerResponse freelancer = freelancersResponses.get(i);
            assertThat(freelancer.getId()).isEqualTo(freelancerIds.get(i));
        }
    }

    @Test
    @DisplayName("키워드를 검색 시, 조회수 순으로 정렬할 수 있다.")
    void searchWithKeywordSortByViewCount() {
        // when
        FreelancersResponse response = searchService.search("k", FreelancerSortType.VIEW_COUNT, 0, 2);

        // then
        List<FreelancerResponse> freelancersResponses = response.getFreelancersResponses();

        List<Long> freelancerIds = List.of(freelancers.get(2).getId(), freelancers.get(0).getId());

        for (int i = 0; i < freelancersResponses.size(); i++) {
            FreelancerResponse freelancer = freelancersResponses.get(i);
            assertThat(freelancer.getId()).isEqualTo(freelancerIds.get(i));
        }
    }

    @Test
    @DisplayName("키워드를 검색하면 검색한 키워드로 시작되는 키워드의 검색 수가 증가된다")
    void searchKeywordIncreaseCount() {
        // given
        searchService.search("k", FreelancerSortType.LATEST, 0, 2);

        // then
        List<Keyword> afterKeywords = keywordRepository.findByNameStartsWith("k");
        List<Long> afterSearchCountList = afterKeywords.stream()
                .map(Keyword::getSearchCount)
                .toList();

        assertThat(afterSearchCountList).containsExactlyInAnyOrder(81L, 71L, 21L);
    }

    @Test
    @DisplayName("10개의 키워드가 검색 수가 많은 순서대로 리턴된다")
    void getHotKeywords() {
        // when
        List<HotKeywordResponse> response = searchService.getHotKeywords().getKeywordsResponses();

        // then
        List<String> keywordNames = response.stream()
                .map(HotKeywordResponse::getName)
                .collect(Collectors.toList());

        assertThat(response.size()).isEqualTo(10);
        assertThat(keywordNames).containsExactly("e", "d", "a", "k1", "k3", "c", "b", "y2", "y3", "k2");
    }
}
