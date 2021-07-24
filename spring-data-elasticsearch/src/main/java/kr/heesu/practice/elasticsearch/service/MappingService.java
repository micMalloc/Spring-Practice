package kr.heesu.practice.elasticsearch.service;

import kr.heesu.practice.elasticsearch.domain.MappingTest;
import kr.heesu.practice.elasticsearch.dto.MappingDto;
import kr.heesu.practice.elasticsearch.repository.MappingTestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class MappingService {

    private final MappingTestRepository mappingRepository;

    public Mono<MappingTest> save(MappingDto.Create create) {
        return mappingRepository.save(create.toEntity());
    }
}
