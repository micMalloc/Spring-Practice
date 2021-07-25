package kr.heesu.practice.r2dbc.repository.impl;

import kr.heesu.practice.r2dbc.entity.Order;
import kr.heesu.practice.r2dbc.repository.custom.impl.CustomOrderRepositoryImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataR2dbcTest
class CustomOrderRepositoryImplTest {

    @Autowired
    CustomOrderRepositoryImpl repository;

    @DisplayName("Custom Repository 주문 조회 테스트")
    @Test
    void findAllOrdersWithMemberTest() {
        // given

        // when
        List<Order> orders = repository.findAllOrdersWithMember()
                .collectList()
                .block();

        // then
        assertNotNull(orders);
        orders.forEach(order -> System.out.println("order = " + order));
    }
}