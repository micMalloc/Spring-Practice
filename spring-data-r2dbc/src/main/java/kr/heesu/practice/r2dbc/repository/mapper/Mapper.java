package kr.heesu.practice.r2dbc.repository.mapper;

import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import kr.heesu.practice.r2dbc.entity.Member;
import kr.heesu.practice.r2dbc.entity.Order;
import kr.heesu.practice.r2dbc.entity.enums.OrderStatus;
import kr.heesu.practice.r2dbc.entity.enums.Roles;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.function.BiFunction;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Mapper {

    public static final BiFunction<Row, RowMetadata, Order> ORDER_MAPPER = (row, rowMetaData) -> Order.builder()
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
