package kr.heesu.practice.r2dbc.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public class BaseEntity {

    protected LocalDateTime createdAt;

    protected LocalDateTime updatedAt;
}
