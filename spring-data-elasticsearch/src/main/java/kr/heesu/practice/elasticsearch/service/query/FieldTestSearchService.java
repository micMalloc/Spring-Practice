package kr.heesu.practice.elasticsearch.service.query;

import kr.heesu.practice.elasticsearch.domain.FieldTest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ReactiveElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import static org.elasticsearch.index.query.QueryBuilders.matchQuery;
import static org.elasticsearch.index.query.QueryBuilders.termQuery;

@Service
@RequiredArgsConstructor
public class FieldTestSearchService {

    private final ReactiveElasticsearchOperations operations;

    public Flux<SearchHit<FieldTest>> termSearch(String field, String content) {
        NativeSearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(termQuery(field, content))
                .build();
        return operations.search(query, FieldTest.class);
    }

    public Flux<SearchHit<FieldTest>> matchSearch(String field, String content) {
        NativeSearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(matchQuery(field, content))
                .withPageable(PageRequest.of(0, 10001))
                .build();
        return operations.search(query, FieldTest.class);
    }

    public Flux<SearchHit<FieldTest>> matchSearch(String field, String content, int size) {
        NativeSearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(matchQuery(field, content))
                .withPageable(PageRequest.of(0, size))
                .build();
        return operations.search(query, FieldTest.class);
    }
}
