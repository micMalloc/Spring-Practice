package kr.heesu.practice.r2dbc.repository;

import kr.heesu.practice.r2dbc.entity.item.Book;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends ItemRepository<Book> {
}
