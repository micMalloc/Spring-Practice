package kr.heesu.practice.jpa.api;

import kr.heesu.practice.jpa.entitiy.Order;
import kr.heesu.practice.jpa.entitiy.OrderSearch;
import kr.heesu.practice.jpa.repository.OrderRepository;
import kr.heesu.practice.jpa.repository.order.simplequery.OrderSimpleQueryDto;
import kr.heesu.practice.jpa.repository.order.simplequery.OrderSimpleQueryRepository;
import kr.heesu.practice.jpa.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * XToOne 관계 성능 최적화
 */
@RequiredArgsConstructor
@RestController
public class OrderSimpleApiController {

    private final OrderService orderService;
    private final OrderRepository orderRepository;
    private final OrderSimpleQueryRepository orderSimpleQueryRepository;

    @GetMapping("/api/v1/simple-orders")
    public List<Order> ordersV1() {
        List<Order> all = orderRepository.findAllByString(new OrderSearch());
        for (Order order : all) {
            order.getMember().getName();
            order.getDelivery().getAddress();
        }
        return all;
    }

    @GetMapping("/api/v2/simple-orders")
    public List<OrderSimpleQueryDto> ordersV2() {
        // ORDER 2개
        // N + 1 문제 --> 1 + 회원 N + 배송 N
        List<Order> orders = orderRepository.findAllByString(new OrderSearch());
        return orders.stream()
                .map(OrderSimpleQueryDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/api/v3/simple-orders")
    public List<OrderSimpleQueryDto> ordersV3() {
        return orderRepository.findAllWithMemberDelivery()
                .stream()
                .map(OrderSimpleQueryDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/api/v4/simple-orders")
    public List<OrderSimpleQueryDto> ordersV4() {
        return orderSimpleQueryRepository.findOrderDtos();
    }
}
