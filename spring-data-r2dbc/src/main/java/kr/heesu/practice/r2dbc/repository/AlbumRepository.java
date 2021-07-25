package kr.heesu.practice.r2dbc.repository;

import kr.heesu.practice.r2dbc.entity.item.Item;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface AlbumRepository extends ReactiveCrudRepository<Item, Long> {

}
