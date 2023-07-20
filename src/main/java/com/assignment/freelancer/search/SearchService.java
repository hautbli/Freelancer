package com.assignment.freelancer.search;

import com.assignment.freelancer.domain.Freelancer;
import com.assignment.freelancer.domain.FreelancerKeyword;
import com.assignment.freelancer.domain.FreelancerRepository;
import com.assignment.freelancer.domain.Keyword;
import com.assignment.freelancer.domain.KeywordRepository;
import com.assignment.freelancer.search.dto.FreelancerResponse;
import com.assignment.freelancer.search.dto.FreelancersResponse;
import com.assignment.freelancer.search.dto.HotKeywordsResponse;
import com.assignment.freelancer.search.dto.KeywordResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService {
    private final KeywordRepository keywordRepository;
    private final FreelancerRepository freelancerRepository;

    public SearchService(KeywordRepository keywordRepository, FreelancerRepository freelancerRepository) {
        this.keywordRepository = keywordRepository;
        this.freelancerRepository = freelancerRepository;
    }

    @Transactional
    public FreelancersResponse search(String keyword, FreelancerSortType sortType, int page, int size) {
        List<Long> keywordIds = keywordRepository.findAllLike(keyword);

        keywordRepository.increaseSearchCount(keywordIds);

        List<Freelancer> freelancers = freelancerRepository.findFreelancers(keywordIds, sortType, page, size);
        List<FreelancerResponse> result = getFreelancersResponse(freelancers);

        return FreelancersResponse.of(result);
    }

    private List<FreelancerResponse> getFreelancersResponse(List<Freelancer> freelancers) {
        return freelancers.stream()
                .map(freelancer -> FreelancerResponse.of(
                        freelancer, getKeywordsResponse(freelancer.getFreelancerKeywords()))
                ).collect(Collectors.toList());
    }

    private List<KeywordResponse> getKeywordsResponse(List<FreelancerKeyword> freelancerKeywords) {
        return freelancerKeywords.stream()
                .map(freelancerKeyword -> new KeywordResponse(
                        freelancerKeyword.getKeyword().getName(), freelancerKeyword.getAbilityLevel())
                ).collect(Collectors.toList());
    }

    public HotKeywordsResponse getHotKeywords() {
        List<Keyword> keywords = keywordRepository.findAllByOrderBySearchCountDesc();

        return HotKeywordsResponse.of(keywords);
    }
}
