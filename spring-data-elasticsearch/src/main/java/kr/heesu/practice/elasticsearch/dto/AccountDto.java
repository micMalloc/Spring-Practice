package kr.heesu.practice.elasticsearch.dto;

import kr.heesu.practice.elasticsearch.domain.Account;
import kr.heesu.practice.elasticsearch.validate.annotation.Searchable;
import kr.heesu.practice.elasticsearch.validate.enums.Order;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.core.SearchHit;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountDto {

    @Builder
    @Getter @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Save {

        @NotNull
        private Long accountNumber;

        @NotNull
        private Long balance;

        @NotBlank
        private String firstname;

        @NotBlank
        private String lastname;

        private int age;

        @NotBlank
        private String gender;

        @NotBlank
        private String address;

        @NotBlank
        private String employer;

        @NotBlank
        private String email;

        @NotBlank
        private String city;

        @NotBlank
        private String state;
    }

    @Builder
    @Getter @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request {
        private int size;
        private List<Object> sort;
    }

    @Data
    public static class Search {

        @NotBlank
        @Searchable(message = "검색 가능한 필드가 아닙니다.")
        private String field;

        @NotBlank
        private String content;

        @NotNull
        private Boolean contain;
    }

    @Builder
    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Sort {
        private String field;

        @NotNull(message = "정렬 순서가 필요합니다.")
        private Order order;
    }

    @Builder
    @Getter @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response {
        private Long accountNumber;
        private Long balance;
        private String firstname;
        private String lastname;
        private int age;
        private String gender;
        private String address;
        private String employer;
        private String email;
        private String city;
        private String state;

        public static Response of(Account account) {
            return Response.builder()
                    .accountNumber(account.getAccountNumber())
                    .address(account.getAddress())
                    .age(account.getAge())
                    .balance(account.getBalance())
                    .city(account.getCity())
                    .email(account.getEmail())
                    .firstname(account.getFirstname())
                    .lastname(account.getLastname())
                    .gender(account.getGender())
                    .employer(account.getEmployer())
                    .state(account.getState())
                    .build();
        }
    }

    @Builder
    @Getter @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ResponseList {
        private int size;
        private List<Object> cursor;
        private List<Response> responses;

        public static ResponseList of(List<SearchHit<Account>> searchHits) {
            int size = searchHits.size();
            return ResponseList.builder()
                    .size(size)
                    .cursor(size == 0 ? Collections.emptyList() : searchHits.get(size - 1).getSortValues())
                    .responses(searchHits.stream().map(hit -> Response.of(hit.getContent())).collect(Collectors.toList()))
                    .build();
        }
    }

    @Builder
    @Getter @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AggBalance {
        private String state;
        private long balance;

        public static AggBalance of(Account account) {
            return new AggBalance(account.getState(), account.getBalance());
        }

        public AggBalance addBalance(AggBalance balance) {
            this.balance += balance.getBalance();
            return this;
        }
    }
}
