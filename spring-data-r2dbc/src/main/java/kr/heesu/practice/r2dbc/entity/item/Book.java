package kr.heesu.practice.r2dbc.entity.item;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@SuperBuilder
public class Book extends Item {

    private String author;
    private String isbn;
}
