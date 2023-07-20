package com.assignment.freelancer.search;

import com.assignment.freelancer.domain.Freelancer;
import com.assignment.freelancer.domain.FreelancerRepository;
import com.assignment.freelancer.domain.Keyword;
import com.assignment.freelancer.domain.KeywordRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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


        // todo 키워드 응답 추가
        return FreelancersResponse.of(freelancers);
    }

    public KeywordsResponse getHotKeywords() {
        List<Keyword> keywords = keywordRepository.findAllByOrderBySearchCountDesc();

        return KeywordsResponse.of(keywords);
    }
}
