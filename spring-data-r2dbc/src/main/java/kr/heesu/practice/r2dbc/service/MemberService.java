package kr.heesu.practice.r2dbc.service;

import kr.heesu.practice.r2dbc.dto.MemberCreateDto;
import kr.heesu.practice.r2dbc.entity.Member;
import kr.heesu.practice.r2dbc.repository.MemberRepository;
import kr.heesu.practice.r2dbc.repository.exception.AlreadyExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Mono<Member> getMember(long id) {
        memberRepository.findByIdWithOrders(id);
        return null;
    }

    public Mono<Member> create(MemberCreateDto.Request requestDto) {
        return memberRepository.existsByName(requestDto.getName())
                .flatMap(isExist -> {
                    if (isExist) {
                        return Mono.error(new AlreadyExistException("이미 존재 합니다."));
                    }
                    return memberRepository.save(requestDto.toEntity());
                });
    }
}
