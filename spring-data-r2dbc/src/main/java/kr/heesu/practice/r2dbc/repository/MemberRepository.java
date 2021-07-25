package kr.heesu.practice.r2dbc.repository;

import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import kr.heesu.practice.r2dbc.entity.Member;
import kr.heesu.practice.r2dbc.repository.custom.CustomMemberRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

public interface MemberRepository extends ReactiveCrudRepository<Member, Long>, CustomMemberRepository {

    BiFunction<Row, RowMetadata, Member> MAPPER = (row, rowMetaData) -> Member.builder()
            .id(row.get("member_id", Long.class))
            .name(row.get("member_name", String.class))
            .build();

    Mono<Boolean> existsByName(String name);
}
