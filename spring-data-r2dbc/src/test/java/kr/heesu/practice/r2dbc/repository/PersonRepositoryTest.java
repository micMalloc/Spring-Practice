package kr.heesu.practice.r2dbc.repository;

import kr.heesu.practice.r2dbc.entity.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataR2dbcTest
class PersonRepositoryTest {

    @Autowired PersonRepository personRepository;

    @DisplayName("Person 저장 테스트")
    @Test
    void saveTest() {
        // given
        Person person = Person.builder()
                .name("이희수")
                .address(Person.Address.builder()
                        .zipCode("zipCode")
                        .street("pungdeokchunro")
                        .city("yong-in")
                        .build())
                .build();

        // when
        Person saved = personRepository.save(person)
                .block();

        // then
        assertNotNull(saved);

        System.out.println("saved = " + saved);
    }
}