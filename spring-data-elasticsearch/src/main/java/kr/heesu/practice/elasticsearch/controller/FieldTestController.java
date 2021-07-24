package kr.heesu.practice.elasticsearch.controller;

import kr.heesu.practice.elasticsearch.dto.FieldTestDto;
import kr.heesu.practice.elasticsearch.service.FieldTestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class FieldTestController {

    private final FieldTestService testService;

    @GetMapping("/field/term")
    public Mono<List<FieldTestDto.Response>> findByTerm(@RequestBody FieldTestDto.Request request) {
        return testService.searchByTerm(request);
    }

    @GetMapping("/field/match")
    public Mono<List<FieldTestDto.Response>> findByMatch(@RequestBody FieldTestDto.Request request) {
        return testService.searchByMatch(request);
    }

    @GetMapping("/field/match/page")
    public Mono<List<FieldTestDto.Response>> findByMatch(
            @RequestBody FieldTestDto.Request request, @RequestParam int size) {
        return testService.searchByMatch(request, size);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Void> create(@RequestBody FieldTestDto.Create create) {
        log.info("Request = {}", create);
        return testService.create(create)
                .then();
    }
}
