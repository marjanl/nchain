package com.nchain.repository;

import com.nchain.entity.NchainApp;
import com.nchain.util.AppType;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Arrays;
import java.util.List;

public class NchainAppSpecification implements Specification<NchainApp> {
    private static final List<String> SEARCHABLE_FIELDS = Arrays.asList("name","type");
    private SearchCriteria criteria;

    public NchainAppSpecification(SearchCriteria searchCriteria) {
        criteria = searchCriteria;
    }

    @Override
    public javax.persistence.criteria.Predicate toPredicate(Root<NchainApp> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        boolean containsValidField = SEARCHABLE_FIELDS.contains(criteria.getKey());
        if(!containsValidField) {
            throw new IllegalArgumentException();
        }
        if(criteria.getKey().equals("type")){
            AppType appType=AppType.valueOf(criteria.getValue());
            return criteriaBuilder.equal(root.get(criteria.getKey()), appType);
        }else {
            return criteriaBuilder.like(root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
        }
    }
}
