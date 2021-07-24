package kr.heesu.practice.jpa.service;

import kr.heesu.practice.jpa.entitiy.Delivery;
import kr.heesu.practice.jpa.entitiy.Member;
import kr.heesu.practice.jpa.entitiy.Order;
import kr.heesu.practice.jpa.entitiy.OrderItem;
import kr.heesu.practice.jpa.entitiy.item.Item;
import kr.heesu.practice.jpa.repository.ItemRepository;
import kr.heesu.practice.jpa.repository.MemberEntityRepository;
import kr.heesu.practice.jpa.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberEntityRepository memberRepository;
    private final ItemRepository itemRepository;

    /**
     * 주문
     */
    @Transactional
    public Long order(Long memberId, Long itemId, int count) {
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        Order order = Order.createOrder(member, delivery, orderItem);

        orderRepository.save(order); // Cascade 로 인하여 order 하나로 delivery, orderItem 모두 저장됌, 서로 같은 생명 주기 안에서만 cascade 옵션 사용하는 것이 좋다. (다른 곳에서 참조 안하고 서로만)
        return order.getId();
    }

    /**
     * 취소
     */
    @Transactional
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findOne(orderId);
        order.cancel(); // 변경 감지를 통해 이 모든게 가능한다.
    }

    /**
     * 검색 - 동적 쿼리 JPA
     */

}
