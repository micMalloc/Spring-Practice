package kr.heesu.practice.r2dbc.entity.item;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.relational.core.mapping.Column;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@SuperBuilder
public class Movie extends Item {

    private String director;

    @Column("movie_etc")
    private String etc;
}
