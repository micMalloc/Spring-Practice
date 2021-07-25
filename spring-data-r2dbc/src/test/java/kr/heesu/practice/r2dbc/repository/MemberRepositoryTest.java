package kr.heesu.practice.r2dbc.repository;

import kr.heesu.practice.r2dbc.entity.Member;
import kr.heesu.practice.r2dbc.entity.enums.Roles;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataR2dbcTest
class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;

    @Disabled("사용하지 않는 테스트")
    @DisplayName("멤버 저장 테스트")
    @Test
    void saveTest() {
        // given
        String name = "사용자 2";

        Member member = Member.builder()
                .name(name)
                .roles(Roles.USER)
                .build();

        // when
        Member saved = memberRepository.save(member)
                .block();

        // then
        assertThat(saved).isNotNull();
        assertThat(saved.getName()).isEqualTo(name);
    }

    @DisplayName("멤버 조회 테스트")
    @Test
    void findByIdTest() {
        // given
        Long id = 1L;

        // when
        Member member = memberRepository.findById(id)
                .block();

        // then
        assertThat(member).isNotNull();
        assertThat(member.getId()).isEqualTo(id);
    }

    @DisplayName("멤버 전체 조회 테스트")
    @Test
    void findAllTest() {
        // given

        // when
        List<Member> members = memberRepository.findAll()
                .collectList()
                .block();

        // then
        assertThat(members).isNotNull();

        members.forEach(member -> System.out.println("member = " + member));
    }

    @DisplayName("")
    @Test
    void findByIdWithOrders() {
        // given
        Long id = 1L;

        // when
        Member member = memberRepository.findByIdWithOrders(id)
                .block();

        // then
        System.out.println("member = " + member);
    }

    @DisplayName("전체 멤버 조회 with 주문 리스트")
    @Test
    void findAllWithOrdersTest() {
        // given

        // when
        List<Member> members = memberRepository.findAllWithOrders()
                .collectList()
                .block();

        // then
        assertNotNull(members);
        assertAll(() -> {
           members.forEach(member -> assertNotNull(member.getOrders()));
        });

        members.forEach(member -> System.out.println("member = " + member));
    }
}