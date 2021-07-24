package kr.heesu.practice.elasticsearch.controller;

import kr.heesu.practice.elasticsearch.dto.AccountDto;
import kr.heesu.practice.elasticsearch.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/elastic/accounts")
@RestController
public class AccountApiController {

    private final AccountService accountService;

    @GetMapping
    public Mono<List<AccountDto.Response>> findAllAccounts() {
        return accountService.searchAllAccounts();
    }

    @GetMapping("/search")
    public Mono<AccountDto.ResponseList> searchProductAfter(@Valid @RequestBody AccountDto.Search request) {
        return accountService.searchAccounts(request);
    }

    @GetMapping("/sorted")
    public Mono<AccountDto.ResponseList> sortProducts(@Valid @RequestBody AccountDto.Sort request) {
        log.info("request = " + request);
        return accountService.searchSortedProducts(request);
    }

    @GetMapping("/balance/{state}")
    public Mono<AccountDto.AggBalance> getTotalBalanceByState(
            @PathVariable String state
    ) {
        log.info("state = {}", state);
        return accountService.aggregateStateBalance(state)
                .doOnSuccess(response -> log.info("response = {}", response))
                .doOnError(error -> log.error(error.getMessage()));
    }
}
