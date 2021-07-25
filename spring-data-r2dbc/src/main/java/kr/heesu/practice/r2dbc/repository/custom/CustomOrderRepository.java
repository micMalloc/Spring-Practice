package kr.heesu.practice.r2dbc.repository.custom;

import kr.heesu.practice.r2dbc.entity.Order;
import reactor.core.publisher.Flux;

public interface CustomOrderRepository {

    Flux<Order> findAllOrdersWithMember();
}
