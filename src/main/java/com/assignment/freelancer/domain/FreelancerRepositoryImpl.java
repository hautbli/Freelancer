package com.assignment.freelancer.domain;

import com.assignment.freelancer.search.FreelancerSortType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

public class FreelancerRepositoryImpl implements CustomFreelancerRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Freelancer> findFreelancers(List<Long> keywordIds, FreelancerSortType sortType, int page, int size) {
        String sort = getSortType(sortType);
        String jpql = "select f from Freelancer f join FreelancerKeyword flkw on flkw.freelancer.id = f.id where flkw.keyword.id in (:keywordIds) order by " + sort + " desc";

        return em.createQuery(jpql, Freelancer.class)
                .setParameter("keywordIds", keywordIds)
                .setFirstResult(page * size)
                .setMaxResults(size)
                .getResultList();
    }

    private String getSortType(FreelancerSortType sortType) {
        if (sortType == FreelancerSortType.ABILITY_LEVEL) {
            return "flkw." + sortType.getSortType();
        }
        return "f." + sortType.getSortType();
    }
}
