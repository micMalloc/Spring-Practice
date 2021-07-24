package kr.heesu.practice.elasticsearch.dto;

import kr.heesu.practice.elasticsearch.domain.FieldTest;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FieldTestDto {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Create {
        private Long no;
        private String text;
        private String keyword;

        public FieldTest toEntity() {
            return new FieldTest(no, no, text, keyword);
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        private String field;
        private String content;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Long id;
        private Long no;
        private String text;
        private String keyword;

        public static Response of(FieldTest test) {
            return new Response(test.getId(), test.getNo(), test.getText(), test.getKeyword());
        }
    }
}
