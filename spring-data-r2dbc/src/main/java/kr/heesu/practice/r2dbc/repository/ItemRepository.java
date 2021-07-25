package kr.heesu.practice.r2dbc.repository;

import kr.heesu.practice.r2dbc.entity.item.Item;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

@NoRepositoryBean
public interface ItemRepository<T extends Item> extends ReactiveCrudRepository<T, Long> {
}
