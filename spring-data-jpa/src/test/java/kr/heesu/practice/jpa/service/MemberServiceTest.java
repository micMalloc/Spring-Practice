package kr.heesu.practice.jpa.service;

import kr.heesu.practice.jpa.entitiy.Member;
import kr.heesu.practice.jpa.repository.MemberEntityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Transactional // 롤백
@SpringBootTest
class MemberServiceTest {

    @Autowired MemberService memberService;

    @Autowired
    MemberEntityRepository memberRepository;

    @Autowired EntityManager em;

    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("kim");

        // when
        Long savedId = memberService.join(member);

        // then
        em.flush();
        assertThat(member).isEqualTo(memberRepository.findOne(savedId)); // 같은 영속성 안에서 (트랜잭션널 단위) id 같다면 같은 객체로 취급
    }

    @Test
    void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        // when
        memberService.join(member1);

        // then
        assertThatThrownBy(() -> memberService.join(member2))
                .isInstanceOf(IllegalStateException.class);
    }
}