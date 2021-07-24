package kr.heesu.practice.elasticsearch.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class ExceptionDto {

    private final int errCode;

    private final String errMsg;
}
