package kr.heesu.practice.elasticsearch.dto;

import kr.heesu.practice.elasticsearch.domain.ListField;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ListFieldDto {

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Create {
        private Long id;
        private List<String> keywords;

        public ListField toEntity() {
            return new ListField(id, keywords);
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response {
        private Long id;
        private List<String> keywords;

        public static Response of(Long id, List<String> keywords) {
            return new Response(id, keywords);
        }
    }
}
