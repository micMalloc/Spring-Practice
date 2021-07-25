package kr.heesu.practice.r2dbc.entity;

import kr.heesu.practice.r2dbc.entity.enums.Roles;
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

import java.util.List;
import java.util.Map;

@ToString
@Table
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Member {

    @Id
    @Column("member_id")
    private Long id;

    @Column("member_name")
    private String name;

    @Column("member_role")
    private Roles roles;

    @Transient
    private List<Order> orders;

    public static Member create(Map<String, Object> row, List<Order> orders) {
        return Member.builder()
                .id((Long) row.get("member_id"))
                .name(String.valueOf(row.get("member_name")))
                .orders(orders)
                .build();
    }

    public Member update(List<Order> orders) {
        this.orders = orders;
        return this;
    }
}
