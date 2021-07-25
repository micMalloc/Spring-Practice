package kr.heesu.practice.r2dbc.repository.custom;

import kr.heesu.practice.r2dbc.entity.Member;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomMemberRepository {

    Mono<Member> findByIdWithOrders(Long id);

    Flux<Member> findAllWithOrders();
}
