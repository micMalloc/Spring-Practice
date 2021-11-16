package kr.heesu.practice.spring.core.advanced.proxy.application.v2;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderServiceV2 {

    private final OrderRepositoryV2 orderRepository;

    public void orderItem(String itemId) {
        orderRepository.save(itemId);
    }
}
