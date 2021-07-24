package kr.heesu.practice.elasticsearch.controller;

import kr.heesu.practice.elasticsearch.dto.MappingDto;
import kr.heesu.practice.elasticsearch.service.MappingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class MappingTestController {

    private final MappingService mappingService;

    @PostMapping("/mapping/create")
    public Mono<Void> create(@RequestBody MappingDto.Create create) {
        return mappingService.save(create)
                .then();
    }
}
