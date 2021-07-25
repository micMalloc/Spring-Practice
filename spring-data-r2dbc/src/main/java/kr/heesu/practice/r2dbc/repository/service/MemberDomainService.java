package kr.heesu.practice.r2dbc.repository.service;

import kr.heesu.practice.r2dbc.entity.Member;
import kr.heesu.practice.r2dbc.entity.Order;
import kr.heesu.practice.r2dbc.repository.MemberRepository;
import kr.heesu.practice.r2dbc.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class MemberDomainService {

    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;

    @Transactional(readOnly = true)
    public Flux<Member> findAllMembers() {
        return memberRepository.findAll()
                .collectMap(Member::getId)
                .flatMapMany(members -> orderRepository.findAllWithMembers(members.keySet())
                    .bufferUntilChanged(Order::getMemberId)
                    .map(orders -> members.get(orders.get(0).getMemberId()).update(orders)));
    }
}
