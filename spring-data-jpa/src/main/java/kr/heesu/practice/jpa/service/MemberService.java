package kr.heesu.practice.jpa.service;

import kr.heesu.practice.jpa.entitiy.Member;
import kr.heesu.practice.jpa.repository.MemberEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor // final 만 잡아준다.
@Transactional(readOnly = true) // public 메서드만 먹는다
@Service
public class MemberService {

    private final MemberEntityRepository memberRepository;

    /**
     * 회원 가입
     */
    @Transactional // 우선적으로 먹는다.
    public Long join(Member member) {
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> members = memberRepository.findByName(member.getName()); // 동시성 문제 가능성 - 디비 제약조건 설정
        if (!members.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원 입니다.");
        }
    }

    // 회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

    @Transactional
    public void update(Long id, String name) {
        Member member = memberRepository.findOne(id);
        member.setName(name);
    }
}
