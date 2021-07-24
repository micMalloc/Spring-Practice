package kr.heesu.practice.elasticsearch.service.query;

import kr.heesu.practice.elasticsearch.domain.Account;
import kr.heesu.practice.elasticsearch.dto.AccountDto;
import kr.heesu.practice.elasticsearch.repository.AccountRepository;
import kr.heesu.practice.elasticsearch.validate.enums.Order;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

@RequiredArgsConstructor
@Service
public class AccountSearchService {

    private final AccountRepository accountRepository;

    public Flux<SearchHit<Account>> searchAll() {
        NativeSearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(matchAllQuery())
                .withSort(new FieldSortBuilder("account_number").order(SortOrder.ASC))
                .build();
        return accountRepository.search(query);
    }

    public Flux<SearchHit<Account>> searchAllWithPagination(int offset, int size) {
        NativeSearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(matchAllQuery())
                .withPageable(PageRequest.of(offset, size))
                .withSort(new FieldSortBuilder("account_number").order(SortOrder.ASC))
                .build();
        return accountRepository.search(query);
    }

    public Flux<SearchHit<Account>> searchAfter(List<Object> sortValue, int size) {
        NativeSearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(matchAllQuery())
                .withPageable(PageRequest.of(0, size))
                .withSort(new FieldSortBuilder("account_number").order(SortOrder.ASC))
                .build();

        query.setSearchAfter(sortValue);
        return accountRepository.search(query);
    }

    public Flux<SearchHit<Account>> searchByState(String state) {
        NativeSearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(matchQuery("state", state))
                .build();
        return accountRepository.search(query);
    }

    public Flux<SearchHit<Account>> search(AccountDto.Sort request) {
        Query query = new NativeSearchQueryBuilder()
                .withSort(
                        new FieldSortBuilder(request.getField())
                                .order(getSortOrder(request.getOrder()))
                )
                .build();
        return accountRepository.search(query);
    }

    private SortOrder getSortOrder(Order order) {
        return Order.DESC.equals(order) ? SortOrder.DESC : SortOrder.ASC;
    }

    public Flux<SearchHit<Account>> search(AccountDto.Search request) {
        Query query = new NativeSearchQueryBuilder()
                .withQuery(getBoolQuery(request))
                .withSort(new FieldSortBuilder("account_number").order(SortOrder.DESC))
                .build();
        return accountRepository.search(query);
    }

    private QueryBuilder getBoolQuery(AccountDto.Search request) {
        if (Boolean.TRUE.equals(request.getContain())) {
            return boolQuery().must(matchQuery(request.getField(), request.getContent()));
        }
        return boolQuery().mustNot(matchQuery(request.getField(), request.getContent()));
    }
}
