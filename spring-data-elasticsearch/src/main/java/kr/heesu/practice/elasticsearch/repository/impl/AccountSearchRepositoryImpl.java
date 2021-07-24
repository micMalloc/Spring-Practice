package kr.heesu.practice.elasticsearch.repository.impl;

import kr.heesu.practice.elasticsearch.constant.IndexConstant;
import kr.heesu.practice.elasticsearch.domain.Account;
import kr.heesu.practice.elasticsearch.repository.AccountSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.core.ReactiveElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@Repository
public class AccountSearchRepositoryImpl implements AccountSearchRepository {

    private final ReactiveElasticsearchOperations operations;

    @Override
    public Flux<SearchHit<Account>> search(Query query) {
        return operations.search(query, Account.class, IndexCoordinates.of(IndexConstant.ACCOUNT));
    }
}
