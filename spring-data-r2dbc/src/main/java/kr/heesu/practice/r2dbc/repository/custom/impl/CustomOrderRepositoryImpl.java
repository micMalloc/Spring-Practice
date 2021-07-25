package kr.heesu.practice.r2dbc.repository.custom.impl;

import kr.heesu.practice.r2dbc.entity.Order;
import kr.heesu.practice.r2dbc.repository.custom.CustomOrderRepository;
import kr.heesu.practice.r2dbc.repository.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
@RequiredArgsConstructor
public class CustomOrderRepositoryImpl implements CustomOrderRepository {

    private final DatabaseClient client;

    @Override
    public Flux<Order> findAllOrdersWithMember() {
        String query =
                "SELECT " +
                "member.member_id, member_name, member_role, " +
                "order_id, order_name, order_status " +
                "FROM orders " +
                "INNER JOIN member " +
                "ON orders.member_id = member.member_id";

        return client.sql(query)
                .map(Mapper.ORDER_MAPPER)
                .all();
    }
}
