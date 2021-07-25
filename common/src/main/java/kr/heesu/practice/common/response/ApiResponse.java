package kr.heesu.practice.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiResponse<T> {

    private final T data;

    private final String message;

    private final Status status;

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(data, "", Status.SUCCESS);
    }

    public static <T> ApiResponse<T> success() {
        return new ApiResponse<>(null, "", Status.SUCCESS);
    }

    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(null, message, Status.FAIL);
    }
}
