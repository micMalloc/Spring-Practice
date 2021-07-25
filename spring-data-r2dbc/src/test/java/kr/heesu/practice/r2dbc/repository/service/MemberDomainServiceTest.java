package kr.heesu.practice.r2dbc.repository.service;

import kr.heesu.practice.r2dbc.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class MemberDomainServiceTest {

    @Autowired MemberDomainService domainService;

    @DisplayName("전체 멤버 조회 with 주문 (WHERE IN)")
    @Test
    void findAllMembersTest() {
        // given

        // when
        List<Member> members = domainService.findAllMembers()
                .collectList()
                .block();

        // then
        assertNotNull(members);

        members.forEach(member -> System.out.println("member = " + member));
    }
}