package kr.heesu.practice.elasticsearch.repository;

import kr.heesu.practice.elasticsearch.domain.MappingTest;
import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository;

public interface MappingTestRepository extends ReactiveElasticsearchRepository<MappingTest, Long> {
}
