package kr.heesu.practice.jpa.service;

import kr.heesu.practice.jpa.entitiy.Address;
import kr.heesu.practice.jpa.entitiy.Member;
import kr.heesu.practice.jpa.entitiy.Order;
import kr.heesu.practice.jpa.entitiy.OrderStatus;
import kr.heesu.practice.jpa.entitiy.item.Book;
import kr.heesu.practice.jpa.exception.NotEnoughStockException;
import kr.heesu.practice.jpa.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired EntityManager em;
    @Autowired OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @Test
    void 상품주문() {
        Member member = getMember();

        Book book = getBook("시골 jpa", 10000, 10);

        int orderCount = 2;

        Long order = orderService.order(member.getId(), book.getId(), orderCount);

        Order one = orderRepository.findOne(order);
        assertEquals(OrderStatus.ORDER, one.getStatus());
        assertEquals(1, one.getOrderItems().size());
        assertEquals(20000, one.getTotalPrice());
        assertEquals(8, book.getStockQuantity());
    }

    @Test
    void 주문취소() {
        Member member = getMember();
        Book book = getBook("시골 jpa", 10000, 10);
        int orderCount = 2;
        Long order = orderService.order(member.getId(), book.getId(), orderCount);

        orderService.cancelOrder(order);

        Order one = orderRepository.findOne(order);

        assertEquals(OrderStatus.CANCEL, one.getStatus());
        assertEquals(10, book.getStockQuantity());
    }

    @Test
    void 재고수량초과() {
        Member member = getMember();
        Book book = getBook("시골 jpa", 10000, 10);

        int orderCount = 11;

        assertThrows(NotEnoughStockException.class,
                () -> orderService.order(member.getId(), book.getId(), orderCount));

    }

    private Book getBook(String name, int price, int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }

    private Member getMember() {
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울", "강가", "123"));
        em.persist(member);
        return member;
    }
}