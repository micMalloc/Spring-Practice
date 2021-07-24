package kr.heesu.practice.jpa.service;

import kr.heesu.practice.jpa.entitiy.item.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;

@SpringBootTest
public class ItemUpdateTest {

    @Autowired EntityManager em;

    @Test
    void updateTest() {
        Book book = em.find(Book.class, 1L);

        // TX
        book.setName("asdfsdfasdf");

        // 변경 감지 == dirty checking
        // TX commit
    }
}
