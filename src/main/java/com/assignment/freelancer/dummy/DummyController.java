package com.assignment.freelancer.dummy;

import com.assignment.freelancer.domain.Freelancer;
import com.assignment.freelancer.domain.FreelancerKeyword;
import com.assignment.freelancer.domain.FreelancerKeywordRepository;
import com.assignment.freelancer.domain.FreelancerRepository;
import com.assignment.freelancer.domain.Keyword;
import com.assignment.freelancer.domain.KeywordRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@RestController
public class DummyController {
    private final FreelancerRepository freelancerRepository;
    private final KeywordRepository keywordRepository;
    private final FreelancerKeywordRepository freelancerKeywordRepository;

    public DummyController(FreelancerRepository freelancerRepository, KeywordRepository keywordRepository, FreelancerKeywordRepository freelancerKeywordRepository) {
        this.freelancerRepository = freelancerRepository;
        this.keywordRepository = keywordRepository;
        this.freelancerKeywordRepository = freelancerKeywordRepository;
    }


    @PostMapping("/dummy")
    @Transactional
    public void createDummy() {
        List<Freelancer> freelancers = List.of(
                new Freelancer(null, 30L),
                new Freelancer(null, 33L),
                new Freelancer(null, 23L),
                new Freelancer(null, 345L),
                new Freelancer(null, 67L)
        );

        List<Keyword> keywords = List.of(
                new Keyword("k1", 30L),
                new Keyword("1", 1L),
                new Keyword("k3", 234L),
                new Keyword("3", 456L),
                new Keyword("k5", 12L),
                new Keyword("5", 467L),
                new Keyword("k6", 123L),
                new Keyword("6", 567L),
                new Keyword("k7", 11L),
                new Keyword("7", 560L)
        );


        for (Freelancer freelancer : freelancers) {
            Freelancer savedFreelancer = freelancerRepository.save(freelancer);
        }

        Random random = new Random();
        for (int i = 0; i < keywords.size(); i++) {
            Keyword savedKeyword = keywordRepository.save(keywords.get(i));
            freelancerKeywordRepository.save(new FreelancerKeyword(freelancers.get(i / 2), savedKeyword,  random.nextInt(100)));
        }
    }
}
