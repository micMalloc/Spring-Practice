package kr.heesu.practice.elasticsearch.repository;

import kr.heesu.practice.elasticsearch.domain.Account;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.Query;
import reactor.core.publisher.Flux;

public interface AccountSearchRepository {

    Flux<SearchHit<Account>> search(Query query);
}
