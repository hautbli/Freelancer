package com.assignment.freelancer.search;

import com.assignment.freelancer.domain.Freelancer;
import com.assignment.freelancer.domain.FreelancerKeywordRepository;
import com.assignment.freelancer.domain.FreelancerRepository;
import com.assignment.freelancer.domain.KeywordRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {
    private final KeywordRepository keywordRepository;
    private final FreelancerRepository freelancerRepository;
    private final FreelancerKeywordRepository freelancerKeywordRepository;

    public SearchService(KeywordRepository keywordRepository, FreelancerRepository freelancerRepository, FreelancerKeywordRepository freelancerKeywordRepository) {
        this.keywordRepository = keywordRepository;
        this.freelancerRepository = freelancerRepository;
        this.freelancerKeywordRepository = freelancerKeywordRepository;
    }

    public FreelancersResponse search(String keyword, FreelancerSortType sortType, int page) {
        PageRequest pageable = PageRequest.of(page, 3, Sort.by(sortType.getSortType()).descending());
        List<Long> keywordIds = keywordRepository.findAllLike(keyword);

        // 기술역량 기준 정렬
        if (sortType.equals(FreelancerSortType.ABILITY_LEVEL)) {
            List<Freelancer> freelancers = freelancerKeywordRepository.searchFreelancers(keywordIds, pageable);
            return FreelancersResponse.of(freelancers);
        }
        List<Freelancer> freelancers = freelancerRepository.searchFreelancers(keywordIds, pageable);

        // todo 키워드 응답 추가
        return FreelancersResponse.of(freelancers);
    }
}
