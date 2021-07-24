package kr.heesu.practice.jpa.repository;

import kr.heesu.practice.jpa.dto.MemberSearchCondition;
import kr.heesu.practice.jpa.dto.MemberTeamDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemberRepositorySupport {

    List<MemberTeamDto> search(MemberSearchCondition condition);

    Page<MemberTeamDto> searchPageSimple(MemberSearchCondition condition, Pageable pageable);

    Page<MemberTeamDto> searchPageComplex(MemberSearchCondition condition, Pageable pageable);
}
