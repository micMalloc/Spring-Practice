package kr.heesu.practice.r2dbc.entity;

import kr.heesu.practice.r2dbc.entity.enums.OrderStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Map;

@ToString(exclude = "member")
@Table("orders")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Order {

    @Id
    @Column("order_id")
    private Long id;

    @Column("member_id")
    private Long memberId;

    @Column("order_name")
    private String name;

    @Column("order_status")
    private OrderStatus status;

    @Transient
    private Member member;

    public static Order create(Map<String, Object> row) {
        Long memberId = (Long) row.get("member_id");
        Long orderId = (Long) row.get("order_id");
        OrderStatus orderStatus = OrderStatus.ORDER;
        String orderName = (String) row.get("order_name");
        String memberName = (String) row.get("member_name");

        Member member = Member.builder()
                .id(memberId)
                .name(memberName)
                .build();
        return new Order(orderId, memberId, orderName, orderStatus, member);
    }
}
