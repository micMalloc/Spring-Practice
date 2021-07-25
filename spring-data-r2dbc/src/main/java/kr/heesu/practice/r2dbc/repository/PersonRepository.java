package kr.heesu.practice.r2dbc.repository;

import kr.heesu.practice.r2dbc.entity.Person;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface PersonRepository extends ReactiveCrudRepository<Person, Long> {

}
