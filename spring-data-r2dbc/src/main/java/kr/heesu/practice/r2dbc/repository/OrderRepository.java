package kr.heesu.practice.r2dbc.repository;

import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import kr.heesu.practice.r2dbc.entity.Member;
import kr.heesu.practice.r2dbc.entity.Order;
import kr.heesu.practice.r2dbc.entity.enums.OrderStatus;
import kr.heesu.practice.r2dbc.entity.enums.Roles;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.util.Set;
import java.util.function.BiFunction;

public interface OrderRepository extends ReactiveCrudRepository<Order, Long> {

    BiFunction<Row, RowMetadata, Order> MAPPER = (row, rowMetaData) -> Order.builder()
            .id(row.get("order_id", Long.class))
            .name(row.get("order_name", String.class))
            .status(OrderStatus.valueOf(row.get("order_status", String.class)))
            .memberId(row.get("member_id", Long.class))
            .member(Member.builder()
                    .name(row.get("member_name", String.class))
                    .id(row.get("member_id", Long.class))
                    .roles(Roles.valueOf(row.get("member_role", String.class)))
                    .build())
            .build();

    @Query("select orders.* from orders where member_id in (:memberIds)")
    Flux<Order> findAllWithMembers(Set<Long> memberIds);

    Flux<Order> findAllByMemberId(Long memberId);

    @Query("SELECT orders.*, member_name, member_role FROM orders JOIN member ON orders.member_id = member.member_id")
    Flux<Order> findAllWithMember();
}
