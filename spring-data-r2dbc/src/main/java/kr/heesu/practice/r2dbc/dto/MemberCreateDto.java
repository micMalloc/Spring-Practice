package kr.heesu.practice.r2dbc.dto;

import kr.heesu.practice.r2dbc.entity.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberCreateDto {

    @Data
    @AllArgsConstructor
    public static class Response {
        private boolean created;
        private String message;

        public static Response of(boolean success, String message) {
            return new Response(success, message);
        }
    }

    @Data
    public static class Request {

        private String name;

        public Member toEntity() {
            return Member.builder().build();
        }
    }
}
