package kr.heesu.practice.r2dbc.entity.item;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.relational.core.mapping.Column;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@SuperBuilder
public class Album extends Item {

    private String artist;

    @Column("album_etc")
    private String etc;
}
