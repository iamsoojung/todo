package com.example.todo.common.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // Internal Server Error
    INTERNAL_SERVER_ERROR("C001", HttpStatus.INTERNAL_SERVER_ERROR, "서버에 오류가 발생했습니다."),
    NOT_FOUND_API_URL("C002", HttpStatus.NOT_FOUND, "요청한 API url을 찾을 수 없습니다."),
    RESOURCE_NOT_FOUND("C003", HttpStatus.NOT_FOUND, "요청한 리소스를 찾을 수 없습니다."),
    ACCESS_DENIED("C004", HttpStatus.FORBIDDEN, "접근이 거부되었습니다.");

    private final String code;
    private final HttpStatus httpStatus;
    private final String message;

}
