package kr.heesu.practice.r2dbc.repository;

import kr.heesu.practice.r2dbc.entity.Order;
import kr.heesu.practice.r2dbc.entity.enums.OrderStatus;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataR2dbcTest
class OrderRepositoryTest {

    @Autowired
    OrderRepository orderRepository;

    @Disabled("사용하지 않는 테스트")
    @DisplayName("주문 저장 테스트")
    @Test
    void saveOrderTest() {
        // given
        int size = 5;
        Long memberId = 2L;
        String name = "주문 ";

        // when
        IntStream.range(0, size)
                .forEach(id -> orderRepository.save(getOrder(memberId, name, id)).block());

        // then
        List<Order> orders = orderRepository.findAllByMemberId(memberId)
                .collectList()
                .block();

        assertThat(orders).isNotNull();
        assertThat(orders.size()).isEqualTo(size);
    }

    @DisplayName("주문 조회 테스트")
    @Test
    void findOrdersTest() {
        // given
        int size = 3;
        Long memberId = 1L;

        // when
        List<Order> orders = orderRepository.findAllByMemberId(memberId)
                .collectList()
                .block();

        // then
        assertNotNull(orders);

        assertEquals(size, orders.size());
        assertAll(() -> {
            orders.forEach(order -> assertEquals(memberId, order.getMemberId()));
        });
    }

    @DisplayName("주문 조회 with 멤버 테스트")
    @Test
    void findByIdWithMemberTest() {
        // given
        Long id = 1L;

        // when
        List<Order> orders = orderRepository.findAllWithMember()
                .collectList()
                .block();

        // then
        assertNotNull(orders);
        
        orders.forEach(order -> System.out.println("order = " + order));
    }

    @DisplayName("주문 조회 WHERE IN 절 사용")
    @Test
    void findAllWithMembersTest() {
        // given
        Set<Long> memberIds = new HashSet<>(Arrays.asList(1L, 2L));

        // when
        List<Order> orders = orderRepository.findAllWithMembers(memberIds)
                .collectList()
                .block();

        // then
        assertNotNull(orders);

        orders.forEach(order -> System.out.println("order = " + order));
    }

    private Order getOrder(Long member, String name, long id) {
        return Order.builder()
                .memberId(member)
                .status(OrderStatus.ORDER)
                .name(name + (id + 1))
                .build();
    }
}