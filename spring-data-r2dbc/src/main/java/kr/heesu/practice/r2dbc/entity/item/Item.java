package kr.heesu.practice.r2dbc.entity.item;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("item")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public class Item {

    @Id
    @Column("item_id")
    protected Long id;

    @Column("item_name")
    protected String name;

    protected int price;

    protected int stockQuantity;
}
