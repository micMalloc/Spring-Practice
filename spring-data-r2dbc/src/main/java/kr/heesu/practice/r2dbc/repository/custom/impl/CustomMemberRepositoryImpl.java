package kr.heesu.practice.r2dbc.repository.custom.impl;

import kr.heesu.practice.r2dbc.entity.Member;
import kr.heesu.practice.r2dbc.entity.Order;
import kr.heesu.practice.r2dbc.entity.enums.OrderStatus;
import kr.heesu.practice.r2dbc.entity.enums.Roles;
import kr.heesu.practice.r2dbc.repository.custom.CustomMemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CustomMemberRepositoryImpl implements CustomMemberRepository {

    private final DatabaseClient client;

    @Override
    public Flux<Member> findAllWithOrders() {
        String query =
                "SELECT " +
                "m.member_id, m.member_name, m.member_role, " +
                "o.order_id, o.order_name, o.order_status " +
                "FROM member as m " +
                "LEFT OUTER JOIN orders as o " +
                "ON m.member_id = o.member_id";

       return client.sql(query)
                .fetch()
                .all()
                .bufferUntilChanged(result -> result.get("member_id"))
                .map(rows ->
                    Member.builder()
                            .id((Long) rows.get(0).get("member_id"))
                            .name(String.valueOf(rows.get(0).get("member_name")))
                            .roles(Roles.valueOf(String.valueOf(rows.get(0).get("member_role"))))
                            .orders(
                                    rows.stream()
                                        .map(row -> Order.builder()
                                                .id((Long) row.get("order_id"))
                                                .memberId((Long) row.get("member_id"))
                                                .name(String.valueOf(row.get("order_name")))
                                                .status(OrderStatus.valueOf(String.valueOf(rows.get(0).get("order_status"))))
                                                .build())
                                        .collect(Collectors.toList())
                            )
                            .build()
                );
    }

    @Override
    public Mono<Member> findByIdWithOrders(Long id) {
        String query =  "SELECT " +
                "member.*, member.member_id as origin_member_id, " +
                "orders.*, orders.member_id as ref_member_id" +
                "from member " +
                "LEFT OUTER JOIN orders " +
                "on orders.member_id = member.member_id " +
                "where member.member_id = :id";

        return client.sql(query)
                .bind("id", id)
                .fetch()
                .all()
                .bufferUntilChanged(result -> result.get("member_id"))
                .map(rows -> {
                    List<Order> orders = rows.stream()
                            .map(Order::create)
                            .collect(Collectors.toList());
                    return Member.create(rows.get(0), orders);
                })
                .collectList()
                .map(list -> list.get(0));
    }
}
