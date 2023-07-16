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
        // 1. keyword 로 keyword list 를 가져온다.
        List<Long> keywordIds = keywordRepository.findAllLike(keyword);

        // 2. keyword list 를 갖고 있는 프리랜서 리스트를 가져온다.
        List<Long> freelancerIds = freelancerKeywordRepository.findAllByKeywordIdIn(keywordIds);

        // 3. pagination 을 적용하여 리턴한다.
        PageRequest pageable = PageRequest.of(page, 3, Sort.by(sortType.getSortType()).descending());
        List<Freelancer> freelancers = freelancerRepository.findAllIdIn(freelancerIds, pageable);

        // 4. 응답 dto로 변환하여 리턴한다.
        return FreelancersResponse.of(freelancers);
    }
}
