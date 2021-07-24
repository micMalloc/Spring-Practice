package kr.heesu.practice.elasticsearch.repository;

import kr.heesu.practice.elasticsearch.domain.ListField;
import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListFieldRepository extends ReactiveElasticsearchRepository<ListField, Long> {
}
