package kr.heesu.practice.elasticsearch.dto;

import kr.heesu.practice.elasticsearch.domain.MappingTest;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MappingDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Create {
        private Long no;
        private String text;
        private String keyword;

        public MappingTest toEntity() {
            return new MappingTest(no, no, text, keyword);
        }
    }
}
