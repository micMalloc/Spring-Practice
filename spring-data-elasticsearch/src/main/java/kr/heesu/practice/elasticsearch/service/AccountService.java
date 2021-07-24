package kr.heesu.practice.elasticsearch.service;

import kr.heesu.practice.elasticsearch.domain.Account;
import kr.heesu.practice.elasticsearch.dto.AccountDto;
import kr.heesu.practice.elasticsearch.repository.AccountRepository;
import kr.heesu.practice.elasticsearch.service.query.AccountSearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountSearchService searchService;

    public Flux<Account> findAll() {
        return accountRepository.findAll();
    }

    public Mono<List<AccountDto.Response>> searchAllAccounts() {
        return searchService.searchAll()
                .map(hit -> AccountDto.Response.of(hit.getContent()))
                .collectList();
    }

    public Mono<AccountDto.ResponseList> searchAfter(AccountDto.Request request) {
        return searchService.searchAfter(request.getSort(), request.getSize())
                .collectList()
                .map(AccountDto.ResponseList::of);
    }

    public Mono<AccountDto.ResponseList> searchAccounts(AccountDto.Search request) {
        return searchService.search(request)
                .collectList()
                .map(AccountDto.ResponseList::of);
    }

    public Mono<AccountDto.ResponseList> searchSortedProducts(AccountDto.Sort request) {
        return searchService.search(request)
                .collectList()
                .map(AccountDto.ResponseList::of);
    }

    public Mono<AccountDto.AggBalance> aggregateStateBalance(String state) {
        return searchService.searchByState(state)
            .map(hit -> AccountDto.AggBalance.of(hit.getContent()))
            .reduce(AccountDto.AggBalance::addBalance);
    }
}
