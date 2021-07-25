package kr.heesu.practice.r2dbc.repository.mapper;

import io.r2dbc.spi.Row;
import kr.heesu.practice.r2dbc.entity.Member;
import kr.heesu.practice.r2dbc.entity.Order;
import kr.heesu.practice.r2dbc.entity.enums.OrderStatus;
import kr.heesu.practice.r2dbc.entity.enums.Roles;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import java.util.NoSuchElementException;

@Slf4j
@ReadingConverter
public class OrderConverter implements Converter<Row, Order> {

    @Override
    public Order convert(Row source) {
        log.info("Convert Order - [Row: {}]", source);
        return Order.builder()
                .id(source.get("order_id", Long.class))
                .name(source.get("order_name", String.class))
                .status(OrderStatus.valueOf(source.get("order_status", String.class)))
                .memberId(source.get("member_id", Long.class))
                .member(getMember(source))
                .build();

    }

    private Member getMember(Row source) {
        try {
            return Member.builder()
                    .name(source.get("member_name", String.class))
                    .id(source.get("member_id", Long.class))
                    .roles(Roles.valueOf(source.get("member_role", String.class)))
                    .build();
        } catch (NoSuchElementException exception) {
            log.warn("No Member for this Order [id: {}]", source.get("order_id", Long.class));
            return Member.builder().build();
        }
    }
}
