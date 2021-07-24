package kr.heesu.practice.elasticsearch.repository;

import kr.heesu.practice.elasticsearch.domain.FieldTest;
import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository;

public interface FieldTestRepository extends ReactiveElasticsearchRepository<FieldTest, Long> {
}
