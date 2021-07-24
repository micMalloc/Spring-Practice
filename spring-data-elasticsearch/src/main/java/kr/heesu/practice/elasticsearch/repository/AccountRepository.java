package kr.heesu.practice.elasticsearch.repository;

import kr.heesu.practice.elasticsearch.domain.Account;
import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository;

public interface AccountRepository extends ReactiveElasticsearchRepository<Account, Long>, AccountSearchRepository {
}
