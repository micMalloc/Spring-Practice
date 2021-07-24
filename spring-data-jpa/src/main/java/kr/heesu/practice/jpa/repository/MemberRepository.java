package kr.heesu.practice.jpa.repository;

import kr.heesu.practice.jpa.entitiy.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositorySupport {

    Optional<Member> findByUsername(String username);
}
