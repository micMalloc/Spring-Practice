package kr.heesu.practice.r2dbc.repository;

import kr.heesu.practice.r2dbc.entity.item.Book;
import kr.heesu.practice.r2dbc.entity.item.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataR2dbcTest
class BookRepositoryTest {

    @Autowired BookRepository bookRepository;

    @DisplayName("책 저장 테스트")
    @Test
    void saveBookTest() {
        // given
        Book book = Book.builder()
                .author("석준")
                .isbn("2020070021")
                .name("Netty In Action")
                .price(1000)
                .stockQuantity(1000)
                .build();

        // when
        Item saved = bookRepository.save(book)
                .block();

        // then
        assertNotNull(saved);
        assertEquals(book.getId(), saved.getId());
    }

    @DisplayName("ID 로 Book 조회 테스트")
    @Test
    void findByIdTest() {
        // given
        Long id = 1L;

        // when
        Book book = bookRepository.findById(id)
                .block();

        // then
        assertNotNull(book);
        assertEquals(id, book.getId());
    }
}