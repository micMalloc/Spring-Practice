package kr.heesu.practice.elasticsearch.service;

import kr.heesu.practice.elasticsearch.domain.FieldTest;
import kr.heesu.practice.elasticsearch.dto.FieldTestDto;
import kr.heesu.practice.elasticsearch.repository.FieldTestRepository;
import kr.heesu.practice.elasticsearch.service.query.FieldTestSearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FieldTestService {

    private final FieldTestSearchService searchService;
    private final FieldTestRepository repository;

    public Mono<List<FieldTestDto.Response>> searchByTerm(FieldTestDto.Request request) {
        return searchService.termSearch(request.getField(), request.getContent())
                .map(SearchHit::getContent)
                .map(FieldTestDto.Response::of)
                .collectList().doOnSuccess(responses -> log.info("Response = {}", responses));
    }

    public Mono<List<FieldTestDto.Response>> searchByMatch(FieldTestDto.Request request) {
        return searchService.matchSearch(request.getField(), request.getContent())
                .map(SearchHit::getContent)
                .map(FieldTestDto.Response::of)
                .collectList().doOnSuccess(responses -> log.info("Response = {}", responses));
    }

    public Mono<FieldTest> create(FieldTestDto.Create create) {
        return repository.save(create.toEntity());
    }

    public Mono<List<FieldTestDto.Response>> searchByMatch(FieldTestDto.Request request, int size) {
        return searchService.matchSearch(request.getField(), request.getContent(), size)
                .map(SearchHit::getContent)
                .map(FieldTestDto.Response::of)
                .collectList().doOnSuccess(responses -> log.info("Response = {}", responses));
    }
}
