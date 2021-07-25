package kr.heesu.practice.r2dbc.repository.mapper;

import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import kr.heesu.practice.r2dbc.entity.Member;
import kr.heesu.practice.r2dbc.entity.Order;
import kr.heesu.practice.r2dbc.entity.enums.OrderStatus;
import kr.heesu.practice.r2dbc.entity.enums.Roles;
import org.springframework.stereotype.Component;

import java.util.function.BiFunction;

@Component
public class OrderMapper implements BiFunction<Row, RowMetadata, Order> {

    @Override
    public Order apply(Row row, RowMetadata rowMetadata) {
        return Order.builder()
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
    }
}
