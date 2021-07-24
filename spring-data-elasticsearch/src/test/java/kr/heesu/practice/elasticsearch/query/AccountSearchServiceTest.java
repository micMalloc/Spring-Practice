package kr.heesu.practice.elasticsearch.query;

import kr.heesu.practice.elasticsearch.domain.Account;
import kr.heesu.practice.elasticsearch.service.query.AccountSearchService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ReactiveElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;


@SpringBootTest
class AccountSearchServiceTest {

    @Autowired ReactiveElasticsearchOperations operations;
    @Autowired AccountSearchService accountService;

    @DisplayName("Search After Test")
    @Test
    void searchAfterTest() {
        // given
        List<Account> entities = accountService.searchAll()
                .map(SearchHit::getContent)
                .collectList().block();

        Query query = Query.findAll();
        query.setPageable(PageRequest.of(0, 100));
        query.addSort(Sort.by(Sort.Direction.ASC, "account_number"));

        List<Object> searchAfter = null;
        List<Account> foundEntities = new ArrayList<>();

        // when
        int loop = 0;
        do {
            query.setSearchAfter(searchAfter);
            List<SearchHit<Account>> searchHits = operations.search(query, Account.class).collectList().block();

            assertThat(searchHits).isNotNull();
            if (searchHits.size() == 0) {
                break;
            }
            foundEntities.addAll(searchHits.stream().map(SearchHit::getContent).collect(Collectors.toList()));
            searchAfter = searchHits.get((searchHits.size() - 1)).getSortValues();

            if (++loop > 10) {
                fail("loop not terminating");
            }
        } while (true);

        // then
        assertThat(foundEntities).containsExactlyElementsOf(entities);
    }
}